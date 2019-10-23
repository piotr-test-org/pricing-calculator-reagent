(ns app.views.product-list
  (:require [app.state :refer [app-state set-currency data]]
            ["@smooth-ui/core-sc" :refer [Box Boxer Input Select Option]]))

(defn product-item [{:keys [id instance license volume_data]}]
  [:> Box {:key id
           :p 1
           :m 3
           :border 1.5
           :border-radius 5}
   [:label "Instance"]
   [:> Input {:value instance :disabled true :m 1}]
   [:label "License"]
   [:> Input {:value license :disabled true :m 1}]
   [:label "Volume"]
   [:> Input {:type :number :value volume_data :disabled true :m 1}]])

(defn product-list []
  [:> Box
   [:h3 "Products"]
   (-> [:> Box]
       (concat (map product-item (:products @app-state)))
       vec)])

