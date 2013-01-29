(ns s-system.client
  (:require [s-system.grammars :as gram]
            [clojure.browser.repl :as repl]))

;; (require 'cljs.repl.browser)
;; (def brepl (cljs.repl.browser/repl-env :port 9000))
;; (cemerick.piggieback/cljs-repl :repl-env (doto brepl cljs.repl/-setup))

(repl/connect "http://localhost:9000/repl")

(.log js/console "Hello there.")

(defn apply-rules [grammar pattern]
  (apply str
         (replace (:rules grammar) pattern)))

(defn gen-commands [grammar n]
  (nth
    (iterate
      (partial apply-rules grammar) (:start grammar))
    n))

(str gram/axial-tree-a)

;; have slider to transition between memoized production lengths
(str (gen-commands gram/axial-tree-a 1))
