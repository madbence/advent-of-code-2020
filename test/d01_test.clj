(ns d01-test
  (:require [d01 :refer :all]
            [clojure.test :refer [deftest is]]))

(deftest pairs
  (is (d01/a "1721\n979\n366\n299\n675\n1456") 514579))

(deftest triplets
  (is (d01/b "1721\n979\n366\n299\n675\n1456") 241861950))
