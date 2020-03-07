(ns libs.patterns)

(def COUNT-X 20)
(def COUNT-Y 20)
(def OFFSET-X 10)
(def OFFSET-Y 10)
(def SIZE-X 4)
(def SIZE-Y 4)
(def COLOR '(150 150 0))


(defn quads [& {:keys [count-x count-y offset-x offset-y size-x size-y]
                :or {count-x COUNT-X count-y COUNT-Y offset-x OFFSET-X offset-y OFFSET-Y size-x SIZE-X size-y SIZE-Y} }]
  (let [points (for [i (range count-x) j (range count-y)] [i, j])]
    (for [[x y] points]
      [(* x offset-x) (* y offset-y)
       size-x size-y])))
