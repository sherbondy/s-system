(defproject s-system "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/clojurescript "0.0-1552"]

                 [com.cemerick/piggieback "0.0.2"
                  :exclusions [org.clojure/clojurescript]]

                 [ring "1.1.6"]
                 [quil "1.6.0"]]

;; make quil-cljs
  :plugins [[lein-cljsbuild "0.3.0"]]
  :source-paths ["src"]
  :hooks [leiningen.cljsbuild]

  :repl-options {
    :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]
  }

  :cljsbuild {
    :repl-listen-port 9000
    :repl-launch-commands
    {"chrome" ["chromium-browser"
               "http://localhost:3000"
               :stdout ".repl-chrome-out"
               :stderr ".repl-chrome-err"]}
    :builds
      [{:id "main"
        :source-paths ["src-cljs"]
        :compiler
        {:output-to "resources/public/js/cljs.js"
         :optimizations :simple
         :pretty-print true}
        :jar true}]}

  :main s-system.server)