(ns app.views.app
  (:require [app.currency.views.currency-selector :refer [currency-selector]]
            [app.products.views.product-list :refer [product-list]]
            [app.prices.views.price-display :refer [price-display]]
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
