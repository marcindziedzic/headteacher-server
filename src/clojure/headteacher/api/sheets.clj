(ns headteacher.api.sheets
  (:require [headteacher.datastore :as d]
            [headteacher.sheets :as s])
  (:use [noir.fetch.remotes :only [defremote]]))

(defremote get-sheet [id]
  (d/get-sheet id))

(defremote add-word [id query]
  (let [sheet (d/get-sheet id)
        word (s/parse-query-string query)]
    (when sheet
      (->>
        (s/add-word sheet word)
        (d/set-sheet id))
      word)))