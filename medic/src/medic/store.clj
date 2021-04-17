(ns medic.store
  (:require [com.stuartsierra.component :as component]
            [clojure.data.json :as json]))

(def baseUrl "http://api.endlessmedical.com/v1/dx/")

(defn get-data
  "Returns data"
  [url] 
  (let [sc (new java.util.Scanner (.openStream url))]
   (def inline "")
   (while (.hasNext sc) (do (def inline (str inline (.nextLine sc)))))
   (.close sc)
   (let [json (json/read-str inline :key-fn keyword)]
     json
     )))

(defn open-connection
  "Opens connection with api"
  [urlp] 
  (let [url (new java.net.URL urlp)
        map {:resp "Error..."
             :name "Sorry..."}
        con (doto (.. url openConnection)
                  (.setInstanceFollowRedirects false)
                  (.connect))]
    (if (.startsWith (str (.getResponseCode con)) "200")
        (get-data url)
        map)))                

(defn getSessionID []
  (let [url (str baseUrl "InitSession")]
    (open-connection url)))
    
(defn read_txt []
   (json/read-str (slurp "SymptomsOutput.json") :key-fn keyword))

(defn getValue [text]
  (let [json (read_txt)]
    (for [x (range 0 4)]
      (if (= (get-in json [x :text]) text)
       (get-in json [x :name])))))

(defn addFeature [input drop sessionID]
  (let [name (getValue drop)
    url (str baseUrl "UpdateFeature?SessionID=" sessionID "&name=" name "&value=" input)]
   (open-connection url)))

(defn analyze [sessionID]
  (let [url (str baseUrl "Analyze?SessionID=" sessionID)]
   (open-connection url)))

(defn add [store content]
  (str content "+1"))

(defn getTopNews [url]
  (open-connection url))

(defrecord InMemoryStore [data]

  component/Lifecycle

  (start [this]
    (assoc this :data (atom {})))

  (stop [this] this))

(defn make-store
  "Returns a new instance of the store component"
  []
  (map->InMemoryStore {}))
