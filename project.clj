(defproject s-system "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.1.6"]
                 [quil "1.6.0"]]
;; make quil-cljs
  :plugins [[lein-cljsbuild "0.2.8"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild { 
    :builds {
      :main {
        :source-path "src-cljs"
        :compiler
        {
          :output-to "resources/public/js/cljs.js"
          :optimizations :simple
          :pretty-print true
        }
        :jar true
      }
    }
  }
  :main s-system.server)

