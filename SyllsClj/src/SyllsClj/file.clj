(ns SyllsClj.file
  (:require 
    [SyllsClj.core :as core]
    [clojure.java.io :as io]
    [clojure.string :as string]))

(defn procString
  ([text]
    (let [sent (string/split text #"(?<=[.,;])")]
      (map string/trim sent)))
  ([text dnf]
    (let [sent (string/split text #"(?<=[.,;])")
          prepSent (map string/trim sent)]
      (cons
        (str dnf
          (first prepSent))
        (rest prepSent)))))

(defn readFile [file]
  (with-open [rdr (io/reader file)]
    (let [out (ref '())
          dnf (ref '"")
          sents (ref '())]
      (doseq [line (line-seq rdr)]
        (println line)
        (dosync
          (if (= @dnf "")
            (alter sents conj (procString line))
            (alter sents conj (procString line dnf))))
;          (if (re-find #"[.!?]$" (last @sents))
;            (ref-set dnf "")
;            (ref-set dnf (last @sents)))))
      @sents))))