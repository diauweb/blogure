(ns xiaodai.blogure.core
  (:require [clojure.tools.logging :as log]
            [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]
            [ring.middleware.resource :as resource]
            [ring.middleware.session :as session]
            [ring.middleware.content-type :as content-type]
            [ring.middleware.params :as params]
            [ring.middleware.not-modified :as not-modified]
            [xiaodai.blogure.config :as cfg]
            [xiaodai.blogure.route :as r]
            [xiaodai.blogure.article :as article]
            [clojure.java.io :as io])
  (:import (java.util Date)
           (java.io File))
  (:gen-class))

(def ^:const version "1.1.0 b170501-A")

(log/info (str "Blogure, version" version))
(log/info (str "#### Xiaodai Present ####"))

(use 'selmer.parser)

(defn wrap-hidder [handler]
  (fn [req]
    (if-let [resp (handler req)]
      (-> resp
          (response/header "Server" "Microsoft-IIS/8.0")
          (response/header "X-Powered-By" "Blogure")))))


(def app (-> r/route
             (resource/wrap-resource "webroot/public")
             (content-type/wrap-content-type)
             (not-modified/wrap-not-modified)
             ;(session/wrap-session {:cookie-attrs {:max-age 3600}})
             (wrap-hidder)
             (params/wrap-params)))

(selmer.parser/set-resource-path! (clojure.java.io/resource "webroot"))
(selmer.parser/add-tag! :blogure-version
                        (fn [& x] version))

(selmer.filters/add-filter! :deref #(deref %))

(defn- start-server []
  (let [cfg (cfg/read-cfg "cfg/main.cfg.json")
        host (:host cfg)
        port (:port cfg)]
    (jetty/run-jetty app {:host host :port port})))

(defn- flush-cache []
  (article/add-new-meta!)
  (article/flush-cache))

(defn -main []
  (flush-cache)
  (start-server))

