(ns headteacher.views.index
  (:use [headteacher.views.layout :only [layout]]
        [noir.core :only [defpage]]
        [net.cgrand.enlive-html :only [defsnippet any-node]]))

(defsnippet index "views/index.html" [:body :> any-node] []
  )

(defpage "/" []
  (layout {:main (index)}))
