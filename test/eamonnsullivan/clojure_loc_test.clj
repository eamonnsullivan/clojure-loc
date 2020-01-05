(ns eamonnsullivan.clojure-loc-test
  (:import java.io.File)
  (:require [clojure.test :refer :all]
            [clojure.spec.test.alpha :as test]
            [eamonnsullivan.clojure-loc :refer :all]))

(deftest spec-tests
  (testing "non-blank?"
    (is (= 1 (:check-passed (test/summarize-results (test/check `non-blank?)))))))

(deftest file-tests
  (testing "has-extension?"
    (is (true? (has-extension? ".xyz" "test.xyz")))
    (is (false? (has-extension? ".txt" "test.xyz"))))
  (testing "clojure-source?"
    (is (true? (clojure-source? "test.clj")))
    (is (false? (clojure-source? "test.cljs"))))
  (testing "clojurescript-source?"
    (is (true? (clojurescript-source? "test.cljs")))
    (is (false? (clojurescript-source? "test.clj")))))
