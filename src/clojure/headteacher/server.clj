(ns headteacher.server
  (:require [noir.server :as server]))

(server/load-views-ns 'headteacher
                      'headteacher.views)

(def handler (server/gen-handler {:mode :dev
                                  :ns 'noir-example}))

(defn -main []
  (server/start
    (Integer/parseInt
      (or (System/getenv "PORT") "8080"))))