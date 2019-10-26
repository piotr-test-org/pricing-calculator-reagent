(ns app.products.mutations
  (:require [app.state :refer [app-state]]
            [app.products.queries :refer [products]]
            [reagent.core :as r]
            [goog.crypt.base64 :refer [encodeString decodeString]]))

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

(defn store-in-url []
  (set! js/window.location.hash (encodeString (products))))

(defn restore-from-url []
  (let [products-from-url (->> (subs js/window.location.hash 1)
                               decodeString
                               cljs.reader/read-string
                               (reduce (fn [acc p] (assoc acc (:id p) p)) {}))]
    (when-not (empty? products-from-url)
      (swap! app-state assoc :products products-from-url))))
