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
    (let [out (ref '())
          dnf (ref '"")
          sents (ref '())]
      (doseq [line (line-seq rdr)]
        (dosync
          (if (= @dnf "")
            (alter sents concat (procString line))
            (alter sents #(concat (drop-last %1) %2) (procString line @dnf))))
        (dosync
          (if (re-find #"[.!?]$" (last @sents))
            (ref-set dnf "")
            (ref-set dnf (last @sents)))))
      @sents)))

(defn procFile [file]
  (let [out (list)]
    (doseq [sent (readFile file)]
      (let [splited (core/splitSent sent)
            word-sylls (hash-map)]
        (pp/pprint splited)
        (map #(assoc(word-sylls (keyword %) (core/countSylls %))) splited)
        (cons word-sylls out)))
    out))