(ns headteacher.workspace
  (:require [enfocus.core :as ef]
            [fetch.remotes :as remotes])
  (:require-macros [enfocus.macros :as em]
                   [fetch.macros :as fm]))

; UTILS

(defn extract [selector function]
  (em/from (em/select [selector]) (function)))

(defn get-value [selector]
  (extract selector #(em/get-prop :value)))

(defn get-text [selector]
  (extract selector #(em/get-text)))

; TRANSFORMATIONS

(em/defaction insert-sheet-content [words]
  ["#loaded-sheet"]   (em/set-style :visibility "visible")
  ["#loaded-sheet p"] (em/clone-for [w words]
                        (em/content (str (key w) " - " (val w)))))

(em/defaction insert-sheet-name [name]
  ["#loaded-sheet-name span"] (em/content name))

(em/defaction clear-sheet-content []
  ["#loaded-sheet"] (em/set-style :visibility "hidden")
  ["#loaded-sheet p:first-child"] (em/content "")
  ["#loaded-sheet p:not(:first-child)"] (em/substitute ""))

(em/defaction insert-word [word]
  ["#loaded-sheet"] (em/set-style :visibility "visible")
  ["#loaded-sheet p:last-child"] (em/after (str "<p>" (first word) " - " (second word) "</p>")))

; EXTRACTORS

(defn get-selected-sheet-name []
  (get-value "#select-sheet"))

(defn get-open-sheet-name []
  (get-text "#loaded-sheet-name span"))

(defn get-query []
  (get-value "#query"))

; PUBLIC API

(defn ^:export load-sheet []
  (fm/remote (get-or-create-sheet (get-selected-sheet-name)) [result]
    (clear-sheet-content)
    (insert-sheet-name (get-selected-sheet-name))
    (when-let [words (seq (:words result))]
      (insert-sheet-content words))))

(defn ^:export submit-query []
  (fm/remote (add-word (get-open-sheet-name) (get-query)) [result]
    (when result
      (insert-word result))))