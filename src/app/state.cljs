(ns app.state
  (:require [reagent.core :as r]))

(defonce app-state (r/atom {:currency :eur
                            :products {1 {:id 1
                                          :instance :running_storage_huge
                                          :license :license_win
                                          :volume_data 100}
                                       2 {:id 2
                                          :instance :running_gpu_medium
                                          :license nil
                                          :volume_data nil}
                                       3 {:id 3
                                          :instance :running_medium
                                          :license nil
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

(defn add-product []
  (let [id (random-uuid)]
    (swap! app-state assoc-in [:products id] {:id id
                                              :instance nil
                                              :license nil
                                              :volume_data nil})))

(defn remove-product [id]
  (swap! app-state update-in [:products] dissoc id))
