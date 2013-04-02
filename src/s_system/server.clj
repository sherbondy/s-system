(ns s-system.server
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :as resources]
            [ring.middleware.reload :as reload]
            [ring.util.response :as response]))

(defn render-app []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body
   (slurp "resources/public/index.html")})

(defn handler [request]
  (render-app))

(def app 
  (-> handler
      (resources/wrap-resource "public")
      (reload/wrap-reload)))

(defn -main [& args]
  (defonce server
    (jetty/run-jetty #'app {:port 3000 :join? false})))
