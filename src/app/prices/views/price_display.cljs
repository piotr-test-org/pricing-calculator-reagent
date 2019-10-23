(ns app.prices.views.price-display
  (:require [app.currency.queries :refer [currency]]
            [app.prices.queries :refer [total-price formatted-price]]
            ["@smooth-ui/core-sc" :refer [Box Text]]))

(defn price-display []
  [:> Box
   [:h2 (str "Total price: " (formatted-price (total-price)))]])
