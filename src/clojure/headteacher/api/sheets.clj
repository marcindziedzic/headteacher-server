(ns headteacher.api.sheets
  (:require [headteacher.datastore :as d])
  (:use [noir.fetch.remotes :only [defremote]]))

(defremote get-sheet [id]
  (d/get-sheet id))