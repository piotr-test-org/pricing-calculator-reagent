(ns app.main
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defonce app-state (r/atom {}))

(def urls
  {:licenses "https://sos-de-muc-1.exo.io/exercises-pricing-data/licenses.json"
   :opencompute "https://sos-de-muc-1.exo.io/exercises-pricing-data/opencompute.json"
   :sos "https://sos-de-muc-1.exo.io/exercises-pricing-data/sos.json"})

(defn fetch-data [source]
  (let [url (source urls)]
    (go (let [response (<! (http/get url {:with-credentials? false}))]
          (swap! app-state assoc-in [:data source] (:body response))))))

(def app
  [:div
   [:h1 "Price Calculator"]])

(defn main! []
  (println "[main]: hello world!")
  (r/render-component app
                      (.getElementById js/document "app")))

(comment
  (fetch-data :sos))
