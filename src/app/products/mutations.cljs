(ns app.products.mutations
  (:require [app.state :refer [app-state]]))

(defn add-product []
  (let [id (random-uuid)]
    (swap! app-state assoc-in [:products id] {:id id
                                              :instance :none
                                              :license :none
                                              :volume_data 0})))

(defn remove-product [id]
  (swap! app-state update-in [:products] dissoc id))

(defn update-product [id attribute value]
  (swap! app-state assoc-in [:products id attribute] value))
