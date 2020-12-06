(ns d06
  (:require [clojure.string :as str]
            [clojure.set :refer [intersection]]))

(defn a [input]
  (->> input
       clojure.string/split-lines
       (partition-by #(= % ""))
       (map #(-> (apply str %) seq set count))
       (reduce +)))

(defn b [input]
  (->> input
       clojure.string/split-lines
       (partition-by #(= % ""))
       (map #(->> % (map (comp set seq)) (reduce intersection) count))
       (reduce +)))
