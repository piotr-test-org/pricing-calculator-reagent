(ns app.views.app
  (:require [app.state :refer [app-state]]))

(defn app []
  [:div
   [:h1 "Price Calculator"]
   [:p (str @app-state)]])
