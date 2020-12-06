(ns d06
  (:require [clojure.string :refer [split-lines]]
            [clojure.set :refer [intersection]]))

(defn a [input]
  (->> input
       split-lines
       (partition-by empty?)
       (map #(-> (apply str %) seq set count))
       (reduce +)))

(defn b [input]
  (->> input
       split-lines
       (partition-by empty?)
       (map #(->> % (map (comp set seq)) (reduce intersection) count))
       (reduce +)))
