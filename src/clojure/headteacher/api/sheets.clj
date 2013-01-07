(ns headteacher.api.sheets
  (:use [noir.fetch.remotes :only [defremote]]))

(def animals
  {:id "24/12/2012"
   :words {
          "dog" "pies"
          "cat" "kot"
          "canary" "kanarek"}})

(def feelings
  {:id "25/12/2012"
   :words {
          "anxious" "zaniepokojony"
          "annoyed" "rozdrażniony"
          "sad" "smutny"
          "happy" "szczęśliwy" }})

(def sheets
  [animals feelings])

(defremote get-sheet [id]
  (first (filter #(= (:id %) id) sheets)))