(ns nature.auto-play.play1)
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [libs.vehicules :as vh]
            [libs.vec :as v]
            [libs.draw :as d]))


(def s [])

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :rgb)
  {:vehicules [(vh/create-vehicule :pos [0 100] :vel [30 50] :max-speed 2 :max-force 10 )
               (vh/create-vehicule :pos [300 300] )]

   })

(defn update-state [state]
  (def s state)
  (let [vehicules (:vehicules state)
        vehicule-a (first vehicules)
        vehicule-b (second vehicules)
        seek-steer-force (vh/seek vehicule-a vehicule-b)
        vehicule-a-forced (vh/apply-force vehicule-a seek-steer-force)
        vehicule-a-updated (vh/update vehicule-a-forced)
        ]
    { :vehicules [vehicule-a-updated vehicule-b] }
    )
  )

(defn draw-state [state]
  (let [vehicules (:vehicules state)]
    (q/background 240)
    (doseq [vehicule vehicules]
      (d/vehicule (:pos vehicule) (vh/calc-theta vehicule) 10) 
      )))


(q/defsketch nature
  :title "You spin my circle right round"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
