(ns SyllsClj.core-test
  (:use clojure.test
        SyllsClj.core))

(deftest syllablesCount
  (testing "Syllables count"
    (is (= (countSylls "abakus") 3))
    (is (= (countSylls "aeon") 2))))