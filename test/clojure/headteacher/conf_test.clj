(ns headteacher.conf-test
  (:use [headteacher.conf :only [parse-uri]]
        [clojure.test :only [deftest run-tests is]]))

(deftest conver-uri-with-credentials-to-map
  (let [uri (parse-uri "scheme://user:password@example.com:8000/")
        {:keys [host port user password]} uri]
    (is (= "example.com" host))
    (is (= 8000 port))
    (is (= "user" user))
    (is (= "password" password))))

(deftest conver-uri-without-credentials-to-map
  (let [uri (parse-uri "scheme://example.com:8000")
    {:keys [host port]} uri]
    (is (= "example.com" host))
    (is (= 8000 port))))

(run-tests)