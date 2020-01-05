(ns eamonnsullivan.clojure-loc-test
  (:require [clojure.test :refer :all]
            [clojure.spec.test.alpha :as test]
            [eamonnsullivan.clojure-loc :refer :all]))

(deftest spec-tests
  (testing "non-blank?"
    (is (= 1 (:check-passed (test/summarize-results (test/check `non-blank?)))))))
