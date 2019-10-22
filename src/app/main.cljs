(ns app.main
  (:require
    [app.data :as data]
    [reagent.core :as r]))

(def app
  [:div
   [:h1 "Price Calculator"]])

(defn main! []
  (println "[main]: Initializing app")
  (data/fetch-all)
  (r/render-component app
                      (.getElementById js/document "app")))

(comment
  (fetch-data :sos))
