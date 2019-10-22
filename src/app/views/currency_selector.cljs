(ns app.views.currency-selector
  (:require [app.state :refer [app-state set-currency]]
            ["@smooth-ui/core-sc" :refer [Box Select]]))

(defn currency-selector []
  [:> Box
    [:label "Currency"]
    [:> Select {:value (:currency @app-state)
                :on-change #(set-currency (.. % -target -value))}
      [:option "chf"]
      [:option "eur"]]])
