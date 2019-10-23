(ns app.views.price-display
  (:require [app.state :refer [currency total-price]]
            ["@smooth-ui/core-sc" :refer [Box Text]]))

(defn formatted-price [price]
  (let [price-str (goog.string/format "%.2f" price)
        currency-str (-> (currency)
                        name
                        clojure.string/upper-case)]
       (str price-str " " currency-str " / h")))

(defn price-display []
  [:> Box
   [:h2 (str "Total price: " (formatted-price (total-price)))]])
