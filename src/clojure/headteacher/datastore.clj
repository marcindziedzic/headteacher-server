(ns headteacher.datastore
  (:require [taoensso.carmine :as redis]
            [headteacher.conf :as conf]))

(def pool (redis/make-conn-pool))

(defmacro with-conn [& body] `(redis/with-conn pool conf/redis-config ~@body))

(defn- sheet-key [id]
  (str "sheet" ":" id))

(defn- sheets-key [username]
  (str "sheets" ":" username))

(defn get-sheets [username]
  (with-conn
    (redis/smembers (sheets-key username))))

(defn set-sheets [username id]
  (with-conn
    (redis/sadd (sheets-key username) id)))

(defn get-sheet [id]
  (with-conn
    (redis/get (sheet-key id))))

(defn set-sheet [id sheet]
  (with-conn
    (redis/set (sheet-key id) sheet)))

(defn create-sheet [username id sheet]
  (set-sheets username id)
  (set-sheet id sheet))

; temporary user, until authorization is implemented TODO move it to view
(def user "anonymous")