(ns libs.draw
  (:require [quil.core :as q]
            [libs.vec :as v]))


(defn vehicule [[x y] theta radius]
  (q/fill 175)
  (q/stroke 0)
  (q/push-matrix)
  (q/translate x y)
  (q/rotate theta)
  (q/begin-shape)
  (q/vertex 0 (* 2 (- radius)))
  (q/vertex (- radius) (* 2 radius))
  (q/vertex radius (* 2 radius))
  (q/end-shape q/CLOSE)
  (q/pop-matrix)

  )
