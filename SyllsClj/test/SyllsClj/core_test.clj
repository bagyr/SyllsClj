(ns SyllsClj.core-test
  (:use clojure.test
        SyllsClj.core
        SyllsClj.file))

(deftest syllablesCount
  (testing "Syllables count"
    (is (= (countSylls "abakus") 3))
    (is (= (countSylls "aeon") 2))))

(deftest testString
  (testing "Single line parsing"
    (is (= (procString "a. a. a.") '("a" "a" "a")))))
