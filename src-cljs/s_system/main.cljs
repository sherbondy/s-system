(ns s-system.main
  (:use [jayq.util :only [log]])
  (:require-macros [jayq.macros :as jm])
  (:require [jayq.core :as jq]
            [s-system.slideshow :as ss]))

(jm/ready
  (log "hi"))