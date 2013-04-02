(ns s-system.main
  (:use [jayq.util :only [log]]
        [jayq.core :only [$]]
        [s-system.core :only [command-list gen-coords-memo]])
  (:require-macros [jayq.macros :as jm])
  (:require [dommy.template :as template]
            [jayq.core :as jq]
            [s-system.display :as d]
            [s-system.grammars :as g]
            [s-system.slideshow :as ss]))

(defn show-hilbert [n]
  (let [h-canvas (aget ($ "#hilbert") 0)
        productions (command-list g/hilbert)]
    (d/display (d/hilbert-applet n) h-canvas)
    (.html ($ "#hilbert-prods")
           (template/node
            [:ul
             (for [i (range n)]
               [:li (str (nth productions i))])]))))

(defn tangle-hilbert []
  (js/Tangle. js/document
    (clj->js
     {:initialize #(this-as this (aset this "productions" 1))
      :update     #(this-as this
                     (show-hilbert (aget this "productions")))})))

(defn tree-applet [angle len prods]
  {:title "Axial Tree A"
   :size [300 500]
   :setup #(gen-coords-memo
             (assoc g/axial-tree-a :angle angle)
              {:origin [150 500]
               :n-productions prods
               :line-length len
               :start-angle 180})})

(defn draw-tree [angle len prods]
  (let [tree-canvas (aget ($ "#tree") 0)]
    (d/display (tree-applet angle len prods) tree-canvas)))

(def tree-defaults
  [["angle" 22.3] 
   ["len" 4]
   ["prods" 4]])

(defn tangle-tree []
  (js/Tangle. (aget ($ "#finale") 0)
    (clj->js
     {:initialize #(this-as this
                    (doseq [[var val] tree-defaults]
                      (aset this var val)))
      :update     (fn []
                    (this-as this
                      (apply draw-tree (map #(aget this (first %))
                                         tree-defaults))))})))

(comment
  (defn setup-leap []
    (.loop js/Leap
      (fn [frame done]
        (let [hands (.-hands frame)]
          (log (.-length (.-pointables frame)))
          (when (> (count hands) 0)
            (log (aget (nth hands 0) "sphereRadius"))))))))

(def turtle-slide 6)
;; x, y, angle
(def turtle-start [100 225 90])
(def step-size 50)
(def turtle-instrs [:f :- :f :- :f :+ :f :f :f :+ :f :+ :f :-
                    :f :- :f :+ :f :+ :f :f :f :+ :f :- :f :- :f])
(def current-step (atom 0))

(def turtle (js/Image.))

(defn load-turtle []
  (aset turtle "src" "/img/turtle.png"))

;; in degrees
(defn deg-to-rad [deg] (/ (* deg Math/PI) 180))

(defn rotate [ctx deg]
  (.rotate ctx (deg-to-rad deg)))

(defn update-turtle-pos [[x y rot] instr]
  (case instr
    :f [(+ x (* step-size (Math/sin (deg-to-rad rot))))
        (- y (* step-size (Math/cos (deg-to-rad rot))))
        rot]
    :+ [x y (+ rot 90)]
    :- [x y (- rot 90)]))

(defn advance-turtle []
  (when (<= @current-step (count turtle-instrs))
    (let [canvas    (aget ($ "#turtle-canvas") 0)
          ctx       (.getContext canvas "2d")
          step      @current-step
          [x y rot] (reduce update-turtle-pos 
                            turtle-start
                            (take step turtle-instrs))]
      (log (str "x: " x ", y: " y ", rot: " rot))
      (.clearRect ctx 0 0 (aget canvas "width") (aget canvas "height"))
      
      (when (> step 0)
        (aset ctx "font" "bold 32px Arial")
        (.fillText ctx 
          (name (nth turtle-instrs (dec step))) 20 50))
      
      (.save ctx)
      (.translate ctx x y)
      (rotate ctx rot)
      (.drawImage ctx turtle 0 0 100 100)
      (.restore ctx)
      (swap! current-step inc))))
  
(add-watch ss/current-slide :action
 (fn [k r o n]
   (when (= n turtle-slide)
     (set! ss/action-fn #(advance-turtle)))))

(jm/ready
  (log "hi")
  (tangle-hilbert)
  (tangle-tree)
  (load-turtle))
