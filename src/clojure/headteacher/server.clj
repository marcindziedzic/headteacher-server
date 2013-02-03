(ns headteacher.server
  (:use [compojure.core :exclude [routes]]
        [compojure.route :only [resources not-found]]
        [ring.middleware.params :only [wrap-params]]
        [ring.adapter.jetty :only [run-jetty]])
  (:require [headteacher.views.index]
            [headteacher.views.workspace]
            [headteacher.api]))

(defroutes routes
  (GET "/" [] (headteacher.views.index/page))
  (GET "/workspace" [] (headteacher.views.workspace/page))

  (ANY "/api/sheet/:id" [] headteacher.api/sheet)
  (ANY "/api/sheet/:id/word" [] headteacher.api/word)

  (resources "/")
  (not-found "Page not found"))

(def handler
  (wrap-params routes))

; move method to config
(defn get-port []
  (Integer/parseInt (or (System/getenv "PORT") "8080")))

(defn -main []
  (run-jetty handler {:port (get-port)}))