(ns s-system.client
  (:use [s-system.core :only [gen-commands]])
  (:require [s-system.display :as d]
            [s-system.grammars :as gram]
            [clojure.browser.repl :as repl]))

;; for rhino:
;; (cemerick.piggieback/cljs-repl)

;; for brepl:
;; (require 'cljs.repl.browser)
;; (def brepl (cljs.repl.browser/repl-env :port 9000))
;; (cemerick.piggieback/cljs-repl :repl-env (doto brepl cljs.repl/-setup))

;; (repl/connect "http://localhost:9000/repl")

(.log js/console "Hello there.")

(str gram/axial-tree-c)

;; have slider to transition between memoized production lengths
(str (gen-commands gram/axial-tree-a 2))

(def plant-canvas (.getElementById js/document "plants"))

(d/display d/tree-a-applet plant-canvas)