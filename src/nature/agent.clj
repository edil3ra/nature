(ns nature.agent
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [nature.entities :as e]
             ))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :rgb)
  {:entity (e/create-entity :vel [1 1])})



(defn update-state [state]
  (assoc state 
         :entity (e/update-entity (:entity state))))



(defn draw-state [state]
  (q/background 240)
  (let [entity (:entity state)
        [x y] (:pos entity)
        [sx sy] (:size entity)]
    (apply q/fill (:color entity))
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      (q/ellipse x y sx sy))))

(q/defsketch nature
  :title "agent"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
