(ns app.currency.views.currency-selector
  (:require [app.currency.queries :refer [currency]]
            [app.currency.mutations :refer [set-currency]]
            ["@smooth-ui/core-sc" :refer [Box Select]]))

(defn currency-selector []
  [:> Box
    [:label "Currency"]
    [:> Select {:value (currency)
                :on-change #(set-currency (.. % -target -value))}
      [:option :chf]
      [:option :eur]]])
