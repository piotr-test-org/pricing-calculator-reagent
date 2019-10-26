(ns app.products.views.product-list
  (:require [app.products.views.product-item :refer [product-item]]
            [app.products.mutations :refer [add-product clear-products]]
            [app.products.queries :refer [products]]
            ["@smooth-ui/core-sc" :refer [Box Button]]))

(defn product-list []
  [:> Box
   [:div
    [:h3 "Products"]
    [:> Button {:variant :danger
                :onClick #(when (js/confirm "Are you sure you want to remove all the products?")
                                (clear-products))}
     "Clear"]]
   (-> [:> Box]
       (concat (map product-item (products)))
       vec)
   [:> Button {:variant :primary :onClick #(add-product)} "Add"]])

