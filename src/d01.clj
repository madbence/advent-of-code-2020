(ns d01
  (:require [clojure.string :refer [split-lines]]
            [utils :refer :all]))

(defn a [input]
  (let [report (->> input split-lines (map ->int))]
    (->> (for [x report
               y report
               :when (= (+ x y) 2020)]
           (* x y))
         first)))

(defn b [input]
  (let [report (->> input split-lines (map ->int))]
    (->> (for [x report
               y report
               z report
               :when (= (+ x y z) 2020)]
           (* x y z))
         first)))
