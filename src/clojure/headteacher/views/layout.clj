(ns headteacher.views.layout
  (:use [net.cgrand.enlive-html :only [deftemplate content]]))

(deftemplate layout "views/layout.html"
  [{:keys [main]}]
  [:body] (content main))