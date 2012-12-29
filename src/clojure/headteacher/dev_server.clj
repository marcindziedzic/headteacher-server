(ns headteacher.dev-server
  (:require [noir.server :as server]))

(server/load-views-ns 'headteacher.api
                      'headteacher.views)

(def handler (server/gen-handler {:mode :dev
                                  :ns 'noir-example}))