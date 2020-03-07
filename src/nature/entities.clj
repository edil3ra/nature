(ns nature.entities)
(def FRAME-RATE 30)

(defn create-entity [& {:keys [acc vel pos color size]
                        :or {acc [0 0] vel [0 0] pos [0 0] color [0 255 0] size [25 25]}}]
  {:acc acc
   :vel vel
   :pos pos
   :color color
   :size size})


(defn update-entity [entity]
  (let [
        acc-framed (mapv #(/ % FRAME-RATE) (:acc entity))
        updated-vel (mapv + (:vel entity) acc-framed)
        updated-pos (mapv + (:pos entity) updated-vel)]
        (assoc entity :vel updated-vel :pos updated-pos)
      )
  )
