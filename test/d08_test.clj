(ns d08-test
  (:require [d08]
            [clojure.test :refer :all]))

(def example
  "nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6")

(deftest a
  (is (= (d08/a example) 5)))

(deftest b
  (is (= (d08/b example) 8)))
