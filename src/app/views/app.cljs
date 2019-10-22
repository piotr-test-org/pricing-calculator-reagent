(ns app.views.app
  (:require [app.state :refer [app-state]]
            ["@smooth-ui/core-sc" :refer [Normalize]]))

(defn header []
  [:h1 "Price Calculator"])

(defn body []
  [:p "body"])

(defn app []
  [:<>
    [:> Normalize]
    [:<>
      [header]
      [body]]])
