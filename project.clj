(defproject blogure "1.0.5"
  :description "A Clojure blog."
  :url "zuodiedai.space"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-json "0.4.0"]
                 [selmer "1.10.2"]
                 [compojure "1.5.2"]
                 [org.clojure/tools.logging "0.3.1"]
                 [markdown-clj "0.9.97"]]
  :plugins [[lein-ring "0.9.7"]]
  :main xiaodai.blogure.core
  :ring {:handler xiaodai.blogure.core/app}
  :aot [xiaodai.blogure.core])
