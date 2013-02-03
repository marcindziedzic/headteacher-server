(ns headteacher.api
  (:require [headteacher.sheets :as s]
            [cheshire.core :as json])
  (:use [liberator.core :only [defresource request-method-in]]))

(defn params [ctx name]
  (-> ctx :request :params (get name)))

(defresource sheet
  :available-media-types ["application/json"]
  :handle-ok #(json/generate-string
                (s/get-or-create-sheet (params % :id))))

(defresource word
  :available-media-types ["application/json"]
  :method-allowed? (request-method-in :put)
  :put! #(s/update-sheet (params % :id) (params % "w")))