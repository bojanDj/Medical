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

(defn index 
  "Handler for route /index"
  [request]
    (res/response (view/index (store/read_txt))))


(defn handler 
  "Returns the web handler function as a closure over the
  application component."
  [store]
  (make-handler ["/" {"index" (partial index)
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
