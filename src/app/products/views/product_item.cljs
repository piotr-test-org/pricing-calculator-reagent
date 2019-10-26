(ns app.products.views.product-item
  (:require [app.prices.queries :refer [product-price formatted-price]]
            [app.products.queries :refer [instances licenses]]
            [app.products.mutations :refer [remove-product update-product]]
            ["@smooth-ui/core-sc" :refer [Box Input Select Option Button]]))

(defn product-item [{:keys [id instance license volume_data] :as product}]
      [:> Box {:key id
               :p 1
               :m 3
               :border 1.5
               :border-radius 5
               :display "flex"
               :flex-direction "row"
               :flex-wrap "wrap"
               :align-items "baseline"
               :justify-content "space-between"}
       [:> Box
        [:label "Instance"]
        (-> [:> Select {:value instance
                        :on-change #(update-product id :instance (keyword (.. % -target -value)))
                        :m 1}]
            (concat (map (fn [[label value]] [:option {:value value} label]) (instances)))
            vec)]
       "+"
       [:> Box
        [:label "License"]
        (-> [:> Select {:value license
                        :on-change #(update-product id :license (keyword (.. % -target -value)))
                        :m 1}]
            (concat (map (fn [[label value]] [:option label]) (licenses)))
            vec)]
       "+"
       [:> Box
        [:label "Volume"]
        [:> Input {:type :number
                   :min 0
                   :max 10000
                   :step 10
                   :value volume_data
                   :on-change #(update-product id :volume_data (.. % -target -value))
                   :m 1}]
        [:label "GB"]]
       "="
       [:> Box
        (formatted-price (product-price product))]
       [:> Button {:onClick #(remove-product id)
                   :variant :danger}
                   ;:ml 5
                   ;:align-self :flex-end}
        "Remove"]])
