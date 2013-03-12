(ns s-system.slideshow
  (:require [jayq.util :as ju]
            [jayq.core :as jq])
  (:require-macros [jayq.macros :as jm])
  (:use [jayq.core :only [$]]))

;; fullscreen
(def full-key 102)
(def left-key 37)
(def right-key 39)

(defn fullscreen [e]
  (.webkitRequestFullScreen e))

(def current-slide (atom 0))

(add-watch current-slide :transition 
  (fn [k r o n]
    (doseq [num [o n]]
      (-> ($ ".slide")
        (.eq num)
        (jq/toggle-class "active")))))

(defn prev-slide []
  (if (> @current-slide 0)
    (swap! current-slide dec)))

(defn next-slide []
  (if (< @current-slide (dec (.-length ($ ".slide"))))
    (swap! current-slide inc)))

(jq/on ($ js/window) "keyup"
    (fn [e]
      (condp = (.-which e)
        full-key  (fullscreen (aget ($ "body") 0))
        left-key  (prev-slide)
        right-key (next-slide)
        false)))

(jm/ready
 (reset! current-slide 0))
