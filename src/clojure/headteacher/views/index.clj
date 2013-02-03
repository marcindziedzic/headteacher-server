(ns headteacher.views.index
  (:use [headteacher.views.layout :only [layout]]
        [net.cgrand.enlive-html :only [defsnippet any-node]]))

(defsnippet index "views/index.html" [:body :> any-node] []
  )

(defn page []
  (layout {:main (index)}))