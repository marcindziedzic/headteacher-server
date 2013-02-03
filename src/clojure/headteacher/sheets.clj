(ns headteacher.sheets
  (:require [headteacher.datastore :as d]))

(def sheet-template
  {:words {}})

(defn- extract-word [query regex]
  (when-let [word (re-find regex query)]
    (clojure.string/replace word #"#|@| *$" "")))

(defn parse-query-string [query]
  (let [f  (partial extract-word query)
        w1 (f #"[#][\w+ ]+")
        w2 (f #"[@][\w+ ]+")]
    (when (and w1 w2) [w1 w2])))

(defn add-word [sheet word]
  (when sheet
    (update-in sheet [:words] conj word)))

; not covered yet with tests, should be simplified

(defn get-or-create-sheet [id]
  (let [sheet (d/get-sheet id)]
    (if-not sheet
      (d/create-sheet d/user id sheet-template)
      sheet)))

(defn update-sheet [id query]
  (let [sheet (d/get-sheet id)
        word (parse-query-string query)]
    (when sheet
      (->>
        (add-word sheet word)
        (d/set-sheet id))
      word)))
