(ns app.currency.queries
  (:require [app.state :refer [app-state]]))

(defn currency []
  (:currency @app-state))

(defn data-in-currency [source]
  (get-in @app-state [:data source (currency)]))
