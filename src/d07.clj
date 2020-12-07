(ns d07
  (:require [utils :refer :all]
            [clojure.string :as str]))

(defn parse-rule [line]
  [(-> (re-matches #"(.*?) bags.*" line)
       (nth 1))
   (->> (re-seq #"(\d+) (.*?) bags?" line)
        (map #(vector (-> % (nth 1) ->int)
                      (-> % (nth 2)))))])

(defn find-containing-colors [colors graph]
  (->> graph
       (filter #(some (fn [[_ color]] (contains? colors color)) (second %)))
       (map first)
       (into colors)))

(defn a [input]
  (let [graph (->> input str/split-lines (map parse-rule) (into {}))]
    (loop [colors #{"shiny gold"}]
      (let [colors' (find-containing-colors colors graph)]
        (if (= colors colors')
          (dec (count colors))
          (recur colors'))))))

(defn count-bags [graph color]
  (->> (get graph color [])
       (map #(* (first %)
                (count-bags graph (second %))))
       (reduce + 1)))

(defn b [input]
  (let [graph (->> input str/split-lines (map parse-rule) (into {}))]
    (dec (count-bags graph "shiny gold"))))
