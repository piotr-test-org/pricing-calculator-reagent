(ns app.main
  (:require
    [app.data :as data]
    [app.views.app :refer [app]]
    [reagent.core :as r]))


(defn main! []
  (println "[main]: Initializing app")
  (data/fetch-all)
  (r/render-component app
                      (.getElementById js/document "app")))

(comment
  (fetch-data :sos))
