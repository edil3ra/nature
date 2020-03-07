(ns sketches.play
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [libs.patterns :as pat]
            ))

(def s [])

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :rgb)
  (let [points (for [i (range default-ellipse-count-x) j (range default-ellipse-count-y)] [i, j])]
    {
     :ellipses-1 (pat/quads :count-x 20 :count-y 20 :size-y 20 :offset-y 25)
     :ellipses-2 (pat/quads :count-x 20 :count-y 20 :size-x 20 :offset-x 25)
     :ellipses-3 (pat/quads :count-x 10 :count-y 10 :size-x 20 :offset-x 50 :size-y 20 :offset-y 55)
     }))

(defn update-state [state]
  (def s state)
  state)

(defn draw-state [state]
  (q/background 0)
  (q/fill 255 255 255)
  (q/with-translation [10 10]
    (doseq [ellipse (:ellipses-1 state)]\
    (q/fill 180 180 210)
      (apply q/ellipse ellipse)))

  (q/with-translation [305 10]
    (doseq [ellipse (:ellipses-1 state)]\
    (q/fill 200 180 210)
      (apply q/ellipse ellipse)))

  (q/with-translation [10 10]
    (q/fill 150 180 220)
    (doseq [ellipse (:ellipses-2 state)]
      (apply q/ellipse ellipse)))

  (q/with-translation [10 305]
    (q/fill 180 180 220)
    (doseq [ellipse (:ellipses-2 state)]
      (apply q/ellipse ellipse)))

  (q/with-translation [25 25]
    (q/fill 220 180 220)
    (doseq [ellipse (:ellipses-3 state)]
      (apply q/ellipse ellipse)))

  )

(q/defsketch nature
  :title "You spin my circle right round"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])


