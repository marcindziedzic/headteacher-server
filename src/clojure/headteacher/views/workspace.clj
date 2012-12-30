(ns headteacher.views.workspace
  (:require [net.cgrand.enlive-html :as html])
  (:use [noir.core :only [defpage]]
        [headteacher.views.index :only [layout]]))

; move this logic to other layer
(def awailable-sheets
  ["24/12/2012" "25/12/2012" "26/12/2012" "27/12/2012"])

(html/defsnippet workspace "views/workspace.html" [:body :> html/any-node] []
  )

(defpage "/workspace" []
  (layout {:main (workspace)}))

