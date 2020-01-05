(ns eamonnsullivan.clojure-loc-test
  (:import java.io.File)
  (:require [clojure.test :refer :all]
            [clojure.spec.test.alpha :as test]
            [eamonnsullivan.clojure-loc :refer :all]))

(defn temp-file
  [prefix suffix]
  (doto (File/createTempFile prefix suffix)
    (.deleteOnExit)))

(deftest spec-tests
  (testing "non-blank?"
    (is (= 1 (:check-passed (test/summarize-results (test/check `non-blank?)))))))

(deftest file-tests
  (testing "has-extension?"
    (let [f (temp-file "something" ".xyz")]
      (is (= true (has-extension? ".xyz" f)))
      (is (= false (has-extension? ".txt" f)))))
  (testing "clojure-source?"
    (let [f (temp-file "something" ".clj")
          f2 (temp-file "something" ".else")]
      (is (= true (clojure-source? f)))
      (is (= false (clojure-source? f2)))))
  (testing "clojurescript-source?"
    (let [f (temp-file "something" ".cljs")
          f2 (temp-file "something" ".else")]
      (is (= true (clojurescript-source? f)))
      (is (= false (clojurescript-source? f2))))))
