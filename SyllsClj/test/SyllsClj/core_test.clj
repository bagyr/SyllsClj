(ns SyllsClj.core-test
  (:use clojure.test
        SyllsClj.core
        SyllsClj.file)
  (:require [clojure.pprint :as pp]))

(deftest syllablesCount
  (testing "Syllables count"
    (is (= (countSylls "abacus") 3))
    (is (= (countSylls "aeon") 2))))

(deftest testString
  (testing "Single line parsing"
    (is (= (procString "a. a. a.") '("a." "a." "a.")))
    (is (= (procString "a. a. a." "bcd") '("bcd a." "a." "a.")))))

(deftest testFile
  (testing "File parse"
    (is (= (readFile "test1.txt") '("asd." "fgh." "qwe.")))
    (is (= (readFile "test2.txt") '("asd." "fgh qwe.")))
    (is (= (readFile "test3.txt") '("asd." "fgh jkl qwe.")))))

(deftest readBigFile
  (testing "Big file"
    (let [a (readFile "prince.txt")]
      (pp/pprint (take 10 a))
      (println (count a)))))

(deftest procBigFile
  (testing "Big file processing")
  (let [out (procFile "prince.txt")]
    (pp/pprint out)))