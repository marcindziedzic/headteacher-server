(ns headteacher.datastore
  (:require [taoensso.carmine :as redis]
            [headteacher.conf :as conf]))

(def pool (redis/make-conn-pool))

(defmacro with-conn [& body] `(redis/with-conn pool conf/redis-config ~@body))

(defn- sheet-key [id]
  (str "sheet" ":" id))

(defn get-sheets [username]
  (with-conn
    (redis/smembers (str "sheets" ":" username))))

(defn get-sheet [id]
  (with-conn
    (redis/get (sheet-key id))))

(defn set-sheet [id sheet]
  (with-conn
    (redis/set (sheet-key id) sheet)))

; temporary initial data population
(def user "anonymous")