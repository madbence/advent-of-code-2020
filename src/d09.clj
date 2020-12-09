(ns d09
  (:require [utils :refer :all]
            [clojure.string :as str]))

(def window-length 26)

(defn check-list [x xs]
  (->> (for [a xs b xs :when (not= a b)] (+ a b))
       (some #(= x %))))

(defn ->long [s]
  (Long/parseLong s))

(defn windows [xs n]
  (->> xs
       (iterate next)
       (take-while some?)
       (map #(take n %))))

(defn a [input]
  (let [xs (->> input str/split-lines (map ->long))]
    (->> (windows xs window-length)
         (filter #(not (check-list (last %) (drop-last %))))
         first
         last)))

(defn b [input]
  (let [xs (->> input str/split-lines (map ->long))
        n (a input)]
    (->> xs
         (reductions conj [])
         (mapcat #(reductions conj [] (reverse %)))
         (filter #(< 1 (count %)))
         (filter #(= n (reduce + %)))
         (map #(+ (reduce min %) (reduce max %)))
         first)))
