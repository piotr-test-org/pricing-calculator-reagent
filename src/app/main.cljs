(ns app.main
  (:require
    [app.data :as data]
    [app.views.app :refer [app]]
    [reagent.core :as r]))

(defn ^:dev/after-load start []
  (r/render-component app
                      (.getElementById js/document "app")))

(defn main! []
  (println "[main]: Initializing app")
  (data/fetch-all)
  (start))

(comment
  (fetch-data :sos))
