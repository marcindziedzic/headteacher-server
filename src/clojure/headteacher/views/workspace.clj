(ns headteacher.views.workspace
  (:require [net.cgrand.enlive-html :as html])
  (:use [noir.core :only [defpage]]
        [headteacher.views.index :only [layout]]))

; move this logic to other layer
(def available-sheets
  ["24/12/2012" "25/12/2012" "26/12/2012" "27/12/2012"])

(html/defsnippet workspace "views/workspace.html" [:body :> html/any-node]
  [sheets]

  [:option]
  (html/clone-for [i (-> sheets count range)]
    (html/set-attr :value (sheets i))))

(defpage "/workspace" []
  (layout {:main (workspace available-sheets)}))

