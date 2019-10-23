(ns app.views.app
  (:require [app.views.currency-selector :refer [currency-selector]]
            [app.views.product-list :refer [product-list]]
            [app.views.price-display :refer [price-display]]
            ["@smooth-ui/core-sc" :refer [Normalize Box]]))

(defn header []
  [:h1 "Price Calculator"])

(defn body []
  [:> Box
   [price-display]
   [currency-selector]
   [product-list]])

(defn app []
  [:<>
    [:> Normalize]
    [:<>
      [header]
      [body]]])
