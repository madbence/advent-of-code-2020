(ns d09-test
  (:require [d09]
            [clojure.test :refer :all]))

(def example
  "35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576")

(deftest a
  (with-redefs [d09/window-length 6]
    (is (= 127 (d09/a example)))))

(deftest b
  (with-redefs [d09/window-length 6]
    (is (= 62 (d09/b example)))))
