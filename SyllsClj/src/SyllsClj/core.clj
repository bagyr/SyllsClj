(ns SyllsClj.core
  (:require [clojure.string :as string]))

(def vowels '("a" "e" "i" "o" "u" "y"))
(def dipth  '("ou" "ie" "oi" "oo" "ea" "ae" "ee" "ai" "oy" "au"))

(defn countSylls [word]
  (let [prepWord (string/lower-case word)
        nVowels (-
          (reduce #(+ (count (re-seq (re-pattern %2) prepWord)) %1) 0 vowels)
          (reduce #(+ (count (re-seq (re-pattern %2) prepWord)) %1) 0 dipth ))]
    (- nVowels
      (#(if
          (and (> nVowels 1) (= (last prepWord) '\e))
          1 0)))))

(defn splitSent [sent]
  (let [splited (string/split sent #"[\p{P} \.\!\?\t\n\r]")]
    (->> splited
      (remove string/blank?)
      (map string/lower-case))))

(defn procSentence [sent]
  (let [words (splitSent sent)]
    (map countSylls words)))

(defn prepPattern [pattern]
  (with-local-vars [acc 0 out '()]
    (doseq [n pattern]
      (var-set out (concat @out (list (+ n @acc))))
      (var-set acc (last @out))
      (println @out))
    @out))

;(defn testPattern [src patt]
;  (with-local-vars [acc 0]
;    (doseq [n src])))