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

(jm/ready
  (log "hi")
  (tangle-hilbert)
  (tangle-tree))
