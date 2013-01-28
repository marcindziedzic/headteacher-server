(ns headteacher.system.workspace-test
  (:require headteacher.datastore)
  (:use [clojure.test :only [deftest testing use-fixtures]]
        [clj-webdriver.taxi]))

(def sheet "example")
(def query "#dog @pies")
(def word  "dog - pies")

(defn- insert-sheet-name [name]
  (send-keys "#select-sheet" name)
  (click (find-element {:value "Load or create sheet"})))

(defn- wait-for-sheet-loaded [name]
  (wait-until #(= name (text "#loaded-sheet-name span"))))

(defn- submit-query [q]
  (send-keys "#query" q)
  (click (find-element {:value "GO"})))

(defn- wait-for-word-added [word]
  (wait-until #(= word (text "#loaded-sheet p:last-child"))))

(deftest ^:system basic-sheet-operations
  (testing "it should be possible to create new sheet"
    (insert-sheet-name sheet)
    (wait-for-sheet-loaded sheet))

  (testing "and then add few words to it"
    (submit-query query)
    (wait-for-word-added word))

  (testing "so that after page reload sheet should be loaded"
    (refresh)
    (insert-sheet-name sheet)
    (wait-for-sheet-loaded sheet)
    (wait-for-word-added word)))

(defn setup [f]
  (headteacher.datastore/clear)
  (set-driver! {:browser :firefox} "http://localhost:5000/workspace")
  (f)
  (quit))

(use-fixtures :once setup)