(ns SyllsClj.core
  (:require [clojure.string :as string]))

(def vowels '("a" "e" "i" "o" "u"))
(def dipth  '("ou" "ie" "oi" "oo" "ea" "ae" "ee" "ai" "oy"))

(defn countSylls [word]
  (let [prepWord (string/lower-case word)
        syllables 0
        dipthongs 0]
        (-
          (reduce #(+ (count (re-seq (re-pattern %2) prepWord)) %1) syllables vowels)
          (reduce #(+ (count (re-seq (re-pattern %2) prepWord)) %1) dipthongs dipth )
          )
      )
  )

(defn splitSent [sent]
  (string/split sent #"[\\p{P} \.\!\?\t\n\\r]")
  )

(defn procSentence [sent]
  (let [words (splitSent sent)]
    (map countSylls words))
  )