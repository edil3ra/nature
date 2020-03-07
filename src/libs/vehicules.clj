(ns libs.vehicules
  (:require [libs.vec :as v]
            [quil.core :as q]))

(def POS (v/zero))
(def ACC (v/zero))
(def VEL (v/zero))
(def MAX-SPEED 4)
(def MAX-FORCE 1)

(defn vh-default []
  {:pos POS
   :vel VEL
   :acc ACC
   :max-speed MAX-SPEED
   :max-force MAX-FORCE})

(defn create-vehicule
  [& {:keys [pos vel acc max-force max-speed]
      :or {pos POS vel VEL acc ACC max-force MAX-FORCE max-speed MAX-SPEED}}]
  {:pos pos
   :vel vel
   :acc acc
   :max-speed max-speed
   :max-force max-force})

(defn seek [source target]
  (let [desired-target (v/sub (:pos target) (:pos source))
        desired-normalize (v/norm desired-target)
        desired-mult (v/mult desired-normalize (:max-speed source))
        steer (v/sub desired-mult (:vel source))
        steer-limited (v/limit steer (:max-force source))]
    steer-limited))


(defn calc-theta [vehicule]
  (let [pos (:vel vehicule)]
    (+ (v/rad pos) (/ q/PI 2))))

(defn update [vehicule]
  (assoc vehicule
         :pos (v/add (:pos vehicule) (:vel vehicule))
         :vel (v/add (:vel vehicule) (:acc vehicule))
         :acc (v/zero)))

(defn apply-force [source force]
  (assoc source :acc (v/add (:acc source) force)))
