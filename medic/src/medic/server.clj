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
  (let [content (get (:form-params request) "content")
        selected (get (:form-params request) "selected")
        sub (store/add store content)]
    (store/addFeature selected content sessionID)
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
