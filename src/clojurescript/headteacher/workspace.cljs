(ns headteacher.workspace
  (:require [enfocus.core :as ef]
            [fetch.remotes :as remotes])
  (:require-macros [enfocus.macros :as em]
                   [fetch.macros :as fm]))

(em/defaction insert-sheet [{words :words}]
  ["#loaded-sheet"]   (em/set-style :visibility "visible")
  ["#loaded-sheet p"] (em/clone-for [w words]
                        (em/content (str (key w) " - " (val w)))))

(em/defaction remove-sheet []
  ["#loaded-sheet"] (em/set-style :visibility "hidden")
  ["#loaded-sheet p:first-child"] (em/content "")
  ["#loaded-sheet p:not(:first-child)"] (em/substitute ""))

(defn get-sheet-name []
  (em/from (em/select ["#select-sheet"]) (em/get-prop :value)))

(defn ^:export load-sheet []
  (fm/remote (get-sheet (get-sheet-name)) [result]
    (remove-sheet)
    (when result
      (insert-sheet result))))
