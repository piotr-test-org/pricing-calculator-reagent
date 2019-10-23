(ns app.views.product-list
  (:require [app.state :refer [data add-product data products instances licenses remove-product update-product]]
            ["@smooth-ui/core-sc" :refer [Box Boxer Input Select Option Button Separator]]))

(defn product-item [{:keys [id instance license volume_data]}]
  [:> Box {:key id
           :p 1
           :m 3
           :border 1.5
           :border-radius 5}
   [:label "Instance"]
   (-> [:> Select {:value instance
                   :on-change #(update-product id :instance (.. % -target -value))
                   :m 1}]
       (concat (map (fn [[label value]] [:option {:value value} label]) (instances)))
       vec)

   [:label "License"]
   (-> [:> Select {:value license
                   :on-change #(update-product id :license (.. % -target -value))
                   :m 1}]
       (concat (map (fn [[label value]] [:option label]) (licenses)))
       vec)
   [:label "Volume"]
   [:> Input {:type :number
              :min 0
              :max 10000
              :value volume_data
              :on-change #(update-product id :volume_data (.. % -target -value))
              :m 1}]
   [:label "GB"]
   [:> Button {:onClick #(remove-product id)
               :variant :danger
               :ml 5
               :align-self :flex-end} "Remove"]])

(defn product-list []
  [:> Box
   [:h3 "Products"]
   (-> [:> Box]
       (concat (map product-item (products)))
       vec)
   [:> Button {:variant :primary :onClick #(add-product)} "Add"]])

