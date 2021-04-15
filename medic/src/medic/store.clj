(ns medic.store
  (:require [com.stuartsierra.component :as component]
            [clojure.data.json :as json]))

(defn read_txt []
   (json/read-str (slurp "SymptomsOutput.json") :key-fn keyword))

(defrecord InMemoryStore [data]

  component/Lifecycle

  (start [this]
    (assoc this :data (atom {})))

  (stop [this] this))

(defn make-store
  "Returns a new instance of the store component"
  []
  (map->InMemoryStore {}))
