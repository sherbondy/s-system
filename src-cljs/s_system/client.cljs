(ns s-system.client
  (:require [s-system.grammars :as gram]
            [s-system.display :as d]
            [clojure.browser.repl :as repl]))

;; for rhino:
;; (cemerick.piggieback/cljs-repl)

;; for brepl:
;; (require 'cljs.repl.browser)
;; (def brepl (cljs.repl.browser/repl-env :port 9000))
;; (cemerick.piggieback/cljs-repl :repl-env (doto brepl cljs.repl/-setup))

(repl/connect "http://localhost:9000/repl")

(.log js/console "Hello there.")

;; core from l-system-fun

(str gram/axial-tree-a)

;; have slider to transition between memoized production lengths
(str (gen-commands gram/axial-tree-a 1))

(def canvas (.getElementById js/document "canvas"))
(d/display d/tree-a-applet canvas)