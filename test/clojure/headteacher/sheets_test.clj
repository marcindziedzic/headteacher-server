(ns headteacher.sheets-test
  (:use [headteacher.sheets]
        [clojure.test :only [deftest run-tests is]]))

(def sheet
  { :created "today"
    :words {"dog" "pies" }})

(def updated-sheet
  { :created "today"
    :words {"dog" "pies"
            "canary" "kanarek"}})

(deftest should-parse-query-string
  (is (= ["dog" "pies"] (parse-query-string "#dog @pies")))
  (is (= ["dog" "pies"] (parse-query-string "@pies #dog")))
  (is (= ["dog" "pies"] (parse-query-string "@pies#dog")))
  (is (= ["home market" "rynek krajowy"] (parse-query-string "#home market @rynek krajowy")))
  (is (= ["home market" "rynek krajowy"] (parse-query-string "  @rynek krajowy  #home market  ")))
  (is (= nil (parse-query-string "@pies")))
  (is (= nil (parse-query-string "#dog")))
  (is (= nil (parse-query-string ""))))

(deftest should-add-word-to-sheet
  (is (= updated-sheet (add-word sheet ["canary" "kanarek"])))
  (is (= sheet (add-word sheet ["dog" "pies"])))
  (is (= sheet (add-word sheet nil)))
  (is (= nil (add-word nil ["canary" "kanarek"])))
  (is (= nil (add-word nil nil))))

(deftest should-define-valid-sheet-template
  (is (contains? sheet-template :words))
  (is (map? (sheet-template :words))))

(run-tests)