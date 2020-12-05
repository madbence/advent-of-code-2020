(ns d05
  (:require [clojure.string :as str]))

(defn decode-row [row]
  (reduce #(+ (* 2 %1) (if (= %2 \B) 1 0)) 0 row))

(defn decode-col [col]
  (reduce #(+ (* 2 %1) (if (= %2 \R) 1 0)) 0 col))

(defn decode-seat [code]
  (+ (* 8 (decode-row (take 7 code)))
     (decode-col (drop 7 code))))

(defn a [input]
  (->> input str/split-lines (map decode-seat) (reduce max)))

(defn b [input]
  (let [seats (->> input str/split-lines (map decode-seat) sort)]
    (->> (map vector seats (rest seats))
         (map #(vector (first %) (- (second %) (first %))))
         (filter #(= (second %) 2))
         first
         first
         inc)))
