(ns s-system.display
  (:use [s-system.core :only [gen-coords]])
  (:require [clojure.string :as str]
            [s-system.grammars :as gram]))

(def ^:dynamic *ctx* nil)
(def ^:dynamic *canvas* nil)

(defn prepend-zeros [s n]
  (let [req (- n (count s))]
    (if (> req 0)
      (str (apply str (repeat req "0")) n)
      s)))

(defn hexify [n]
  (let [ndef n]
    (prepend-zeros (.toString ndef 16) 2)))

(defn hex-string [rgb]
  (str "#" (apply str (map hexify rgb))))

(defn fill [& rgb]
  (let [hex-str (hex-string rgb)]
    (aset *ctx* "fillStyle" hex-str)))

(defn stroke-color [& rgb]
  (let [hex-str (hex-string rgb)]
    (aset *ctx* "strokeStyle" hex-str)))

(defn background [& rgb]
  (apply fill rgb)
  (.fillRect *ctx* 0 0 (.-width *canvas*) (.-height *canvas*)))

(defn stroke-weight [n]
  (aset *ctx* "lineWidth" n))

;; durr, could just do rgb(a)

(defn line [x1 y1 x2 y2]
  (.moveTo *ctx* x1 y1)
  (.lineTo *ctx* x2 y2)
  (.stroke *ctx*))

(defn plot-system [coordinates]
  (dorun
   (map #(apply line %) coordinates)))

(defn- setup [coordinates]
  (.log js/console "setting up")
  (background 255 255 255)
  (stroke-color 0 160 0)
  (stroke-weight 1.4)
  (plot-system coordinates))

(defn display [applet canvas]
  (doseq [[attr index] [["width" 0] ["height" 1]]]
    (aset canvas attr (get-in applet [:size index])))

  (binding [*canvas* canvas
            *ctx*    (.getContext canvas "2d")]
    ((:setup applet))))

(def tree-a-applet
  {:title "Axial Tree A"
   :size [400 600]
   :setup #(setup
            (gen-coords gram/axial-tree-a
                        {:origin [200 600]
                         :n-productions 4
                         :line-length 6
                         :start-angle 180}))})

(def tree-c-applet
  {:title "Axial Tree C"
   :size [400 600]
   :setup #(setup
            (gen-coords gram/axial-tree-c
                        {:origin [200 600]
                         :n-productions 4
                         :line-length 6
                         :start-angle 180}))})

(def triangle-applet
  {:title "Sierpinski Triangle"
   :size [400 600]
   :setup #(setup
            (gen-coords gram/sierpinski-triangle
                        {:origin [80 360]
                         :n-productions 6
                         :line-length 4
                         :start-angle 90}))})