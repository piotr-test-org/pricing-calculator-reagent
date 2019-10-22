(ns app.main
  (:require [reagent.core :as r]))

(def app
  [:div
   [:h1 "Price Calculator"]])

(defn main! []
  (println "[main]: hello world!")
  (r/render-component app
                      (.getElementById js/document "app")))
