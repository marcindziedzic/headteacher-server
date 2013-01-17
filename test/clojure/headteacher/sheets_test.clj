(ns headteacher.sheets-test
  (:use [headteacher.sheets]
        [clojure.test :only [deftest run-tests is]]))

(deftest should-parse-query-string
  (is (= {"dog" "pies"} (parse-query-string "#dog @pies")))
  (is (= {"dog" "pies"} (parse-query-string "@pies #dog")))
  (is (= {"dog" "pies"} (parse-query-string "@pies#dog")))
  (is (= {"home market" "rynek krajowy"} (parse-query-string "#home market @rynek krajowy")))
  (is (= {"home market" "rynek krajowy"} (parse-query-string "  @rynek krajowy  #home market  ")))
  (is (= nil (parse-query-string "@pies")))
  (is (= nil (parse-query-string "#dog")))
  (is (= nil (parse-query-string ""))))

(run-tests)