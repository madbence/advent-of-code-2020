(ns d06-test
  (:require [d06]
           [clojure.test :refer :all]))

(def example
  "abc

a
b
c

ab
ac

a
a
a
a

b")

(deftest test-a
  (is (= 11 (d06/a example))))

(deftest test-b
  (is (= 6 (d06/b example))))
