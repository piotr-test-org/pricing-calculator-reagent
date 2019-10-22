(ns app.data
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [app.state :refer [app-state]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(def urls
  {:licenses "https://sos-de-muc-1.exo.io/exercises-pricing-data/licenses.json"
   :opencompute "https://sos-de-muc-1.exo.io/exercises-pricing-data/opencompute.json"
   :sos "https://sos-de-muc-1.exo.io/exercises-pricing-data/sos.json"})

(defn fetch-data [source]
  (let [url (source urls)]
    (println (str "[data]: Fetching data from " url))
    (go (let [response (<! (http/get url {:with-credentials? false}))]
          (swap! app-state assoc-in [:data source] (:body response))))))

(defn fetch-all []
  (doall (map fetch-data (keys urls))))
