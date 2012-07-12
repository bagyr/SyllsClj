(ns SyllsClj.core-test
  (:use clojure.test
        SyllsClj.core
        SyllsClj.file))

(deftest syllablesCount
  (testing "Syllables count"
    (is (= (countSylls "abacus") 3))
    (is (= (countSylls "aeon") 2))))

(deftest testString
  (testing "Single line parsing"
    (is (= (procString "a. a. a.") '("a." "a." "a.")))
    (is (= (procString "a. a. a." "bcd") '("bcda." "a." "a.")))))

(deftest testFile
  (testing "File parse"
    (is (= (readFile "test1.txt") '("asd. fgh. qwe.")))
    (is (= (readFile "test2.txt") '("asd. fghqwe.")))))
