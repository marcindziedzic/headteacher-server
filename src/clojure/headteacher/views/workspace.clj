(ns headteacher.views.workspace
  (:require [net.cgrand.enlive-html :as html])
  (:use [noir.core :only [defpage]]
        [headteacher.views.index :only [layout]]
        [headteacher.datastore :only [get-sheets populate user]]))

(html/defsnippet workspace "views/workspace.html" [:body :> html/any-node]
  [sheets]

  [:option]
  (html/clone-for [s sheets]
    (html/set-attr :value s)))

(defpage "/workspace" []
  (layout {:main (workspace (get-sheets user))}))

(defpage "/populate" []
  (populate)
  "OK")