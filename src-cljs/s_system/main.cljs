(ns s-system.main
  (:use [jayq.util :only [log]]
        [jayq.core :only [$]]
        [s-system.core :only [command-list]])
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

(defn tangle []
  (js/Tangle. js/document
    (clj->js
     {:initialize #(this-as this (aset this "productions" 1))
      :update     #(this-as this
                     (show-hilbert (aget this "productions")))})))

(jm/ready
  (log "hi")
  (tangle))
