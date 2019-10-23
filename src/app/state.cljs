(ns app.state
  (:require [reagent.core :as r]
            [clojure.string :as str]))

(defonce app-state (r/atom {:currency :eur
                            :products {1 {:id 1
                                          :instance :running_storage_huge
                                          :license :license_win
                                          :volume_data 100}
                                       2 {:id 2
                                          :instance :running_gpu_medium
                                          :license :none
                                          :volume_data 0}
                                       3 {:id 3
                                          :instance :running_medium
                                          :license :none
                                          :volume_data 200}}}))

(defn set-currency [currency]
  (swap! app-state assoc :currency (keyword currency)))

(defn currency []
  (:currency @app-state))

(defn products []
  (-> @app-state
      :products
      vals))

(defn data [source]
  (get-in @app-state [:data source (currency)]))

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
