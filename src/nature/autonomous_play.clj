(ns nature.autonomous-play
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [libs.vehicules :as vh]
            [libs.vec :as v]
            [libs.draw :as d]))


(def s [])

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :rgb)
  {:vehicules [(vh/create-vehicule :pos [200 100] :vel [1 1] :max-speed 10 :max-force 0.1 )
               (vh/create-vehicule :pos [300 300] :vel [1 0] :max-speed 18 :max-force 0.1 )]

   })

(defn update-state [state]
  (def s state)
  (let [vehicules (:vehicules state)
        vehicule-a (first vehicules)
        vehicule-b (second vehicules)
        seek-steer-force-a (vh/seek vehicule-a vehicule-b)
        vehicule-a-forced (vh/apply-force vehicule-a seek-steer-force-a)
        vehicule-a-updated (vh/update vehicule-a-forced)

        seek-steer-force-b (vh/seek vehicule-b vehicule-a)
        vehicule-b-forced (vh/apply-force vehicule-b seek-steer-force-b)
        vehicule-b-updated (vh/update vehicule-b-forced)
        ]
    { :vehicules [vehicule-a-updated vehicule-b-updated] }
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
