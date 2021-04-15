(ns medic.store
  (:require [com.stuartsierra.component :as component]))

(defrecord InMemoryStore [data]

  component/Lifecycle

  (start [this]
    (assoc this :data (atom {})))

  (stop [this] this))

(defn make-store
  "Returns a new instance of the store component"
  []
  (map->InMemoryStore {}))
