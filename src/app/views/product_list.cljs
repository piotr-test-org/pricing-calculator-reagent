(ns app.views.product-list
  (:require [app.views.product-item :refer [product-item]]
            [app.state :refer [data add-product products]]
            ["@smooth-ui/core-sc" :refer [Box Button]]))

(defn product-list []
  [:> Box
   [:h3 "Products"]
   (-> [:> Box]
       (concat (map product-item (products)))
       vec)
   [:> Button {:variant :primary :onClick #(add-product)} "Add"]])

