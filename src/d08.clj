(ns d08
  (:require [clojure.string :as str]
            [utils :refer :all]))

(defn decode-instruction [line]
  {:type (case (nth line 0)
           \a :acc
           \n :nop
           \j :jmp)
   :arg (-> (re-matches #".* ((\+|-)\d+)" line) second ->int)})

(defn decode-program [code]
  {:pc 0
   :acc 0
   :code (mapv decode-instruction code)})

(defn step [program]
  (let [instruction (nth (:code program) (:pc program))]
    (case (:type instruction)
      :nop (update program :pc inc)
      :acc (-> program
               (update :acc + (:arg instruction))
               (update :pc inc))
      :jmp (update program :pc + (:arg instruction)))))

(defn terminates? [prg]
  (loop [visited #{}
         prg prg]
    (cond
      (not (contains? (:code prg) (:pc prg))) (:acc prg)
      (contains? visited (:pc prg)) nil
      true (recur (conj visited (:pc prg))
                  (step prg)))))

(defn swap-nop-jmp [instruction]
  (case (:type instruction)
    :jmp (assoc instruction :type :nop)
    :nop (assoc instruction :type :jmp)
    :acc instruction))


(defn a [input]
  (loop [visited #{}
         prg (decode-program (str/split-lines input))]
    (if (contains? visited (:pc prg))
      (:acc prg)
      (recur (conj visited (:pc prg))
             (step prg)))))

(defn b [input]
  (let [prg (decode-program (str/split-lines input))]
    (->> (range 0 (count (:code prg)))
         (map #(update-in prg [:code %] swap-nop-jmp))
         (some terminates?))))
