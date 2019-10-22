(ns app.state
  (:require [reagent.core :as r]))

(defonce app-state (r/atom {:currency "chf"}))

(defn set-currency [currency]
  (swap! app-state assoc :currency currency))
