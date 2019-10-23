(ns app.views.app
  (:require [app.views.currency-selector :refer [currency-selector]]
            [app.views.product-list :refer [product-list]]
            ["@smooth-ui/core-sc" :refer [Normalize Box]]))

(defn header []
  [:h1 "Price Calculator"])

(defn body []
  [:> Box
   [currency-selector]
   [product-list]])

(defn app []
  [:<>
    [:> Normalize]
    [:<>
      [header]
      [body]]])
