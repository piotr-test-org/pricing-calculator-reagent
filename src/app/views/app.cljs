(ns app.views.app
  (:require [app.views.currency-selector :refer [currency-selector]]
            ["@smooth-ui/core-sc" :refer [Normalize Box]]))

(defn header []
  [:h1 "Price Calculator"])

(defn body []
  [:> Box
   [currency-selector]])

(defn app []
  [:<>
    [:> Normalize]
    [:<>
      [header]
      [body]]])
