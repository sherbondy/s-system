(ns s-system.slideshow
  (:require [jayq.util :as ju]
            [jayq.core :as jq])
  (:require-macros [jayq.macros :as jm])
  (:use [jayq.core :only [$]]))

;; fullscreen
(def full-key 102)

(defn fullscreen [e]
  (.requestFullScreen e))

(jq/on ($ js/window) "keypress"
    (fn [e]
      (if (= (.-which e) full-key)
        (js/alert "full"))))