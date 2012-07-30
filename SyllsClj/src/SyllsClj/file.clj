(ns SyllsClj.file
  (:require [SyllsClj.core :as core]
            [clojure.java.io :as io]
            [clojure.string :as string]
            [clojure.pprint :as pp]))
;  (:use [clojure.tools.logging :only (info)]))

(defn procString
  ([text]
    (let [sent (string/split text #"(?<=[.!?])")]
      (map string/trim sent)))
  ([text dnf]
    (let [sent (string/split text #"(?<=[.!?])")
          prepSent (map string/trim sent)]
      (cons
        (str dnf " "
          (first prepSent))
        (rest prepSent)))))

(defn readFile [file]
  (with-open [rdr (io/reader file)]
    (with-local-vars [out '(), dnf '"", sents '()]
      (doseq [line (line-seq rdr)]
        (if (= @dnf "")
          (var-set sents (concat @sents (procString line)))
          (var-set sents (#(concat (drop-last %1) %2) @sents (procString line @dnf))))
        (if (re-find #"[.!?]$" (last @sents))
          (var-set dnf '"")
          (var-set dnf (last @sents))))
      @sents)))

(defn procFile [file]
  (with-local-vars [out '()]
    (doseq [sent (readFile file)]
      (var-set out (cons (core/procSentence sent) @out)))
    @out))