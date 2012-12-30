(ns headteacher.views.index
  (:require [net.cgrand.enlive-html :as html])
  (:use [noir.core :only [defpage]]))

(html/deftemplate layout "views/layout.html"
  [{:keys [main]}]
  [:body] (html/content main))

(html/defsnippet index "views/index.html" [:body :> html/any-node] []
  )

(defpage "/" []
  (layout {:main (index)}))
