(ns medic.store
  (:require [com.stuartsierra.component :as component]
            [clojure.data.json :as json]
            [clojure.string :as str]
            [clojure.tools.logging :as log]
            [clj-http.client :as client])
  (:import java.net.URLEncoder))

(def baseUrl "http://api.endlessmedical.com/v1/dx/")

(defn get-data
  "Returns data"
  [url] 
  (let [sc (new java.util.Scanner (.openStream url))]
   (def inline "")
   (while (.hasNext sc) (do (def inline (str inline (.nextLine sc)))))
   (.close sc)
   (let [json (json/read-str inline :key-fn keyword)]
     json)))

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

(defn open-connection-post
  "Opens connection with api POST"
  [url sessionID passphrase] 
   (log/info (client/post (str baseUrl "AcceptTermsOfUse?SessionID=" (URLEncoder/encode sessionID "UTF-8") "&passphrase=" (URLEncoder/encode passphrase "UTF-8")))))

(defn get-session-id 
   "Gets api session id"
  []
  (let [urlSession (str baseUrl "InitSession")
       terms "I have read, understood and I accept and agree to comply with the Terms of Use of EndlessMedicalAPI and Endless Medical services. The Terms of Use are available on endlessmedical.com"
       sessionID ((open-connection urlSession) :SessionID)]
   (open-connection-post (str baseUrl "AcceptTermsOfUse") sessionID terms)
    sessionID))
    
(defn read-txt 
  "Read data from json questions file"
  []
   (json/read-str (slurp "SymptomsOutput.json") :key-fn keyword))

(defn get-value 
  "Gets specific value from json questions file"
  [text name]
  (let [json (read-txt)]
    (for [x (range 0 4)]
      (if (= (get-in json [x :text]) text)
       (get-in json [x (keyword name)])))))

(defn add-feature 
  "Adds symptom"
  [input drop sessionID]
  (let [name (get-value drop "name")
        min (get-value drop "min")
        max (get-value drop "max")
        cho (get-value drop "choices")
        url (str baseUrl "UpdateFeature?SessionID=" sessionID "&name=" name "&value=" input)]
    (log/info (Integer/parseInt input))
    (log/info (Integer/parseInt input))
    (if (not (clojure.string/blank? (str/join max)))
	   (if (< (Double/parseDouble input) (Double/parseDouble (str/join max)))
	     (if ( > (Double/parseDouble input) (Double/parseDouble (str/join min)))
	       (let [json (open-connection url)]
	         (str "Symptom inputed."))
	       (str "Symptom must be higher then " (str/join min) " and lower then " (str/join max)))
	     (str "Symptom must be higher then " (str/join min) " and lower then " (str/join max)))
    (if (or (= (Integer/parseInt input) 2) (= (Integer/parseInt input) 3))
      (let [json (open-connection url)]
        (str "Symptom inputed."))
      (str "Type 2 if answer is No. Type 3 is answer is Yes.")))))

(defn analyze 
  "Analyzes symptoms and gets result"
  [sessionID]
  (let [url (str baseUrl "Analyze?SessionID=" sessionID)]
  (log/info url)
  (open-connection url)))

(defn get-top-news
  "Gets health news from API for index page"
  [url]
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
