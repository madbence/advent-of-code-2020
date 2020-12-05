(ns d05-test
  (:require [d05]
            [clojure.test :refer :all]))

(deftest test-decode-row
  (is (= 44 (d05/decode-row "FBFBBFF"))))

(deftest test-decode-col
  (is (= 5 (d05/decode-col "RLR"))))

(deftest test-decode-seat
  (is (= 357 (d05/decode-seat "FBFBBFFRLR"))))
