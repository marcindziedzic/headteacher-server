(ns headteacher.datastore
  (:require [taoensso.carmine :as redis]
            [headteacher.conf :as conf]))

; make functions private ^:private
(def pool (redis/make-conn-pool))

(defmacro with-conn [& body] `(redis/with-conn pool conf/redis-config ~@body))

; production used functions
(defn get-sheets [username]
  (with-conn
    (redis/smembers (str "sheets" ":" username))))

(defn get-sheet [id]
  (with-conn
    (redis/get (str "sheet" ":" id))))

; temporary initial data population
(def user "anonymous")
(def sheet-names ["24/12/2012" "25/12/2012" "26/12/2012" "27/12/2012" "28/12/2012" "29/12/2012" "30/12/2012" "31/12/2012"])

(def animals
  {:words {
            "dog" "pies"
            "cat" "kot"
            "canary" "kanarek"}})

(def feelings
  {:words {
            "anxious" "zaniepokojony"
            "annoyed" "rozdrażniony"
            "sad" "smutny"
            "happy" "szczęśliwy" }})

(defn populate []
  (with-conn
    (doall (map #(redis/sadd "sheets:anonymous" %) sheet-names))
    (redis/set "sheet:24/12/2012" animals)
    (redis/set "sheet:25/12/2012" feelings)))