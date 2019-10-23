(ns app.views.product-item
  (:require [app.state :refer [instances licenses remove-product update-product product-price]]
            [app.views.price-display :refer [formatted-price]]
            ["@smooth-ui/core-sc" :refer [Box Input Select Option Button]]))

(defn product-item [{:keys [id instance license volume_data] :as product}]
      [:> Box {:key id
               :p 1
               :m 3
               :border 1.5
               :border-radius 5}
       [:label "Instance"]
       (-> [:> Select {:value instance
                       :on-change #(update-product id :instance (keyword (.. % -target -value)))
                       :m 1}]
           (concat (map (fn [[label value]] [:option {:value value} label]) (instances)))
           vec)

       [:label " + License"]
       (-> [:> Select {:value license
                       :on-change #(update-product id :license (keyword (.. % -target -value)))
                       :m 1}]
           (concat (map (fn [[label value]] [:option label]) (licenses)))
           vec)
       [:label " + Volume"]
       [:> Input {:type :number
                  :min 0
                  :max 10000
                  :step 10
                  :value volume_data
                  :on-change #(update-product id :volume_data (.. % -target -value))
                  :m 1}]
       [:label "GB"]
       (str " = " (formatted-price (product-price product)))
       [:> Button {:onClick #(remove-product id)
                   :variant :danger
                   :ml 5
                   :align-self :flex-end} "Remove"]])
