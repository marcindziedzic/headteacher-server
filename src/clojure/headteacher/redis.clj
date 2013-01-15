(ns headteacher.redis
  (:require [taoensso.carmine :as redis]
            [headteacher.conf :as conf]))

(def ^:private pool (redis/make-conn-pool))

(defmacro with-conn [& body] `(redis/with-conn pool conf/redis-config ~@body))