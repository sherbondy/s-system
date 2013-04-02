(ns s-system.slideshow
  (:require [jayq.util :as ju]
            [jayq.core :as jq])
  (:require-macros [jayq.macros :as jm])
  (:use [jayq.core :only [$]]))

;; fullscreen
(def full-key 70)
(def left-key 37)
(def right-key 39)
(def action-key 32)

(defn fullscreen [e]
  (.webkitRequestFullScreen e))

(def current-slide (atom 0))
(def action-fn #())

(add-watch current-slide :transition 
  (fn [k r o n]
    (set! (.-hash js/location) n)
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
      (condp = (.-keyCode e)
        full-key   (fullscreen (aget ($ "body") 0))
        left-key   (prev-slide)
        right-key  (next-slide)
        action-key (action-fn)
        (ju/log e))))

(defn hash-no [loc-hash]
  (try 
    (let [num (js/parseInt (subs loc-hash 1) 10)]
      (if (>= num 0)
        num 0))
    (catch js/Exception e 0)))

(jm/ready
 (let [n (hash-no (.-hash js/location))]
   (reset! current-slide n)))
