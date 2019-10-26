(ns app.products.views.product-list
  (:require [app.products.views.product-item :refer [product-item]]
            [app.products.mutations :refer [add-product clear-products]]
            [app.products.queries :refer [products]]
            ["@smooth-ui/core-sc" :refer [Box Button]]))

(defn product-list []
  [:> Box {:display "flex" :flex-direction "column" :justify-content "center"}
   [:> Box {:display "flex" :flex-direction "row" :justify-content "flex-start"}
    [:h3 "Products"]
    [:> Button {:variant :danger
                :m 2
                :onClick #(when (js/confirm "Are you sure you want to remove all the products?")
                                (clear-products))}
     "Clear"]]
   (-> [:> Box {:style {:display "flex" :flex-direction "column"}}]
       (concat (map product-item (products)))
       vec)
   [:> Button {:variant :primary
               :style {:flex-basis 1}
               :onClick #(add-product)} "Add"]])

