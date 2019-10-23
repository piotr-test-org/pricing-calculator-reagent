(ns app.currency.mutations
  (:require [app.state :refer [app-state]]))

(defn set-currency [currency]
  (swap! app-state assoc :currency (keyword currency)))
