(ns medic.server
  (:require [com.stuartsierra.component :as component]
            [bidi.ring :refer [make-handler resources]]
            [aleph.http :as http]
            [ring.util.response :as res]
            [ring.util.request :as req]
            [ring.middleware.params :refer [wrap-params]]
            [medic.view :as view]
            [medic.config :refer :all]
            [medic.store :as store]))

(def sessionID (store/getSessionID))

(defn handle-post 
  "Handler for route /search if request method is POST"
  [sessionID store request]
  (let [input (get (:form-params request) "content")
        drop (get (:form-params request) "drop")]
    (store/addFeature input drop sessionID)
    (res/redirect "search" :see-other)))

(defn handle-search 
  "Handler for route /search if request method is not POST"
  [request]
  (res/response (view/index (store/read_txt))))

(defn search-handler 
  "Handler for route /search"
  [store request]
	  (if (= (:request-method request) :post)
	    (handle-post sessionID store request)
	    (handle-search request)))

(defn index 
  "Handler for route /index"
  [request]
    (res/response (view/index (store/read_txt))))

(defn about 
  "Handler for route /index"
  [request]
  (let [url "http://newsapi.org/v2/top-headlines?country=us&category=health&apiKey=5c9694ca2b7147039cd6614c21cb361c"]
    (res/response (view/about (store/getTopNews url)))))

(defn score-handler 
  "Handler for route /index"
  [store request]
    (res/response (view/score (store/analyze sessionID))))

(defn handler 
  "Returns the web handler function as a closure over the
  application component."
  [store]
  (make-handler ["/" {"index" (partial index)
                      "search" (partial search-handler store)
                      "score" (partial score-handler store)
                      "about" (partial about)
                      "" (resources {:prefix "public/"})}]))

(defn app
  "Returns the web handler function as a closure over the
  application component."
  [store]
  (-> (handler store)
      wrap-params))

(defrecord WebServer [server]

  component/Lifecycle

  (start [this]
    (assoc this :server (http/start-server (app (:store this)) {:port port})))

  (stop [this]
    (dissoc this :server)))

(defn make-server
  "Returns a new instance of the web server component"
  []
  (map->WebServer {}))
