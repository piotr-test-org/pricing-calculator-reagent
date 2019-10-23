(ns app.prices.queries
  (:require [app.state :refer [data]]
            [app.products.queries :refer [products]]))

(defn instance-price [instance]
  (case instance
    :none 0
    (-> (data :opencompute)
        instance
        js/parseFloat)))

(defn license-price [license]
  (case license
    :none 0
    (-> (data :licenses)
        license
        js/parseFloat)))

(defn storage-price [volume]
  (-> (data :sos)
      :storage_volume
      js/parseFloat
      (* volume)))

(defn product-price [{:keys [instance license volume_data]}]
  (+ (instance-price instance) (license-price license) (storage-price volume_data)))

(defn total-price []
  (->> (products)
       (map product-price)
       (reduce + 0)))

(defn formatted-price [price]
  (let [price-str (goog.string/format "%.2f" price)
        currency-str (-> (currency)
                        name
                        clojure.string/upper-case)]
       (str price-str " " currency-str " / h")))
