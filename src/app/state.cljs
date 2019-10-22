(ns app.state
  (:require [reagent.core :as r]))

(defonce app-state (r/atom {:currency :eur}))

(defn set-currency [currency]
  (swap! app-state assoc :currency (keyword currency)))
