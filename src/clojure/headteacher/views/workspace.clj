(ns headteacher.views.workspace
  (:require [net.cgrand.enlive-html :as html])
  (:use [noir.core :only [defpage]]
        [headteacher.views.index :only [layout]]))

(def available-sheets
  ["24/12/2012" "25/12/2012" "26/12/2012" "27/12/2012" "28/12/2012"])

(html/defsnippet workspace "views/workspace.html" [:body :> html/any-node]
  [sheets]

  [:option]
  (html/clone-for [s sheets]
    (html/set-attr :value s)))

(defpage "/workspace" []
  (layout {:main (workspace available-sheets)}))

