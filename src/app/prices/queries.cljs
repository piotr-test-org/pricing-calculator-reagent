(ns app.prices.queries
  (:require [app.currency.queries :refer [currency data-in-currency]]
            [app.products.queries :refer [products]]))

(defn instance-price [instance]
  (case instance
    :none 0
    (-> (data-in-currency :opencompute)
        instance
        js/parseFloat)))

(defn license-price [license]
  (case license
    :none 0
    (-> (data-in-currency :licenses)
        license
        js/parseFloat)))

(defn storage-price [volume]
  (-> (data-in-currency :sos)
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
