(ns headteacher.sheets)

(defn- extract-word [query regex]
  (when-let [word (re-find regex query)]
    (clojure.string/replace word #"#|@| *$" "")))

(defn parse-query-string [query]
  (let [f  (partial extract-word query)
        w1 (f #"[#][\w+ ]+")
        w2 (f #"[@][\w+ ]+")]
    (when (and w1 w2) {w1 w2})))