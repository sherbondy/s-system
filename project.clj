(defproject s-system "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [ring "1.1.6"]
                 [quil "1.6.0"]
                 
                 [jayq "2.3.0"]
                 [prismatic/dommy "0.0.2"]]

  :plugins [[lein-cljsbuild "0.3.0"]]
  :source-paths ["src", "src-cljs"]

  :cljsbuild {
    :builds
      [{:id "main"
        :source-paths ["src-cljs"]
        :compiler
        {:output-to "resources/public/js/main.js"
         :optimizations :simple
         :pretty-print true}}]}

  :main s-system.server)
