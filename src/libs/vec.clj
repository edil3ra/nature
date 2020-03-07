(ns libs.vec
  (:require [quil.core :as q]))

(def v1 [10 10])
(def v2 [5 5])

(defn zero [] [0 0])
(defn left [] [-1 0])
(defn right [] [1 0])
(defn top [] [0 -1])
(defn bottom [] [0 1])

(defn add [v1 v2]
  (map + v1 v2))

(defn sub [v1 v2]
  (map - v1 v2))

(defn dist2 [[x y]]
  (+ (* x x) (* y y)))

(defn mult [v m]
  (mapv (fn [x] (* x m)) v))

(defn dist [v]
  (q/sqrt (dist2 v)))

(defn norm [[x y]]
  (let [d (dist [x y] )]
       [(/ x d) (/ y d)]))

(defn limit [v max]
  (if  (> (dist v) max)
    (mult (norm v) max) v))

(defn rad [[x y]]
  (q/atan2 x y))

(defn deg [v]
  (q/degrees (rad v)))

(defn to-polar [v]
  [(dist v) (rad v)])

(defn from-polar [mag rad]
  [(* (q/cos rad) mag) (* (q/sin rad) mag)])
