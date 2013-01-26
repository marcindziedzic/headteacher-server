(ns headteacher.sheets-test
  (:use [headteacher.sheets]
        [clojure.test :only [deftest is are]]))

(def sheet
  { :created "today"
    :words {"dog" "pies" }})

(def updated-sheet
  { :created "today"
    :words {"dog" "pies"
            "canary" "kanarek"}})

(deftest should-parse-query-string
  (are [qs result] (= result (parse-query-string qs))
    "#dog @pies"                        ["dog" "pies"]
    "@pies #dog"                        ["dog" "pies"]
    "@pies#dog"                         ["dog" "pies"]
    "#home market @rynek krajowy"       ["home market" "rynek krajowy"]
    "  @rynek krajowy  #home market  "  ["home market" "rynek krajowy"]
    "@pies"                             nil
    "#dog"                              nil
    ""                                  nil)
  )

(deftest should-add-word-to-sheet
  (are [word sheet result] (= result (add-word sheet word))
    ["canary" "kanarek"] sheet updated-sheet
    ["dog" "pies"]       sheet sheet
    nil                  sheet sheet
    ["canary" "kanarek"] nil   nil
    nil                  nil   nil))

(deftest should-define-valid-sheet-template
  (is (contains? sheet-template :words))
  (is (map? (sheet-template :words))))