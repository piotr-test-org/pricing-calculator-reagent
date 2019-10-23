(ns app.currency.queries
  (:require [app.state :refer [app-state]]))

(defn currency []
  (:currency @app-state))