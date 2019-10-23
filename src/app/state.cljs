(ns app.state
  (:require [reagent.core :as r]))

(defonce app-state (r/atom {:currency :eur
                            :products [{:id 1
                                        :instance :running_storage_huge
                                        :license :license_win
                                        :volume_data 100}
                                       {:id 2
                                        :instance :running_gpu_medium
                                        :license nil
                                        :volume_data nil}
                                       {:id 3
                                        :instance :running_medium
                                        :license nil
                                        :volume_data 200}]}))

(defn set-currency [currency]
  (swap! app-state assoc :currency (keyword currency)))

(defn currency []
  (:currency @app-state))

(defn products []
  (:products @app-state))

(defn data [source]
  (get-in @app-state [:data source (currency)]))
