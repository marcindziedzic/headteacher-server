(ns headteacher.api.sheets
  (:use [noir.core :only [defpage]]
        [noir.response :only [json]]))

(def animals
  {:id 1
   :words {
          "dog" "pies"
          "cat" "kot"
          "canary" "kanarek"}})

(def feelings
  {:id 2
   :words {
          "anxious" "zaniepokojony"
          "annoyed" "rozdrażniony"
          "sad" "smutny"
          "happy" "szczęśliwy" }})

(def sheets
  [animals feelings])

(defpage "/api/sheets" []
  (json sheets))