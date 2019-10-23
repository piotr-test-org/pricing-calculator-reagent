(ns app.products.queries
  (:require [app.state :refer [app-state data]]
            [clojure.string :as str]))

(defn products []
  (-> @app-state
      :products
      vals))

(defn instances []
      (->> :opencompute
           data
           vec
           (filter (fn [[key val]] (-> key
                                       str
                                       (str/split #"_")
                                       first
                                       (= ":running"))))
           (map (fn [[key val]] (let [name (-> key
                                               str
                                               (str/split #"_")
                                               rest
                                               vec)]
                                     [(str/join " " name) key])))
           (cons ["-" :none])))

(defn licenses []
      (cons [:none "0.0"] (vec (data :licenses))))
