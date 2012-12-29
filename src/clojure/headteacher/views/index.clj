(ns headteacher.views.index
  (:require [net.cgrand.enlive-html :as html])
  (:use [noir.core :only [defpage]]))

(html/deftemplate index "views/index.html" []
  )

(defpage "/" []
  (index))
