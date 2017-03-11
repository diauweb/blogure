(ns xiaodai.blogure.render
  (:require [xiaodai.blogure.config :as cfg]
            [xiaodai.blogure.nav :as nav]
            [selmer.parser :as ps]))


(defn- environment []
  (-> "cfg/main.cfg.json" cfg/read-cfg :site-cfg))

(defn render-file [^String name ^Number id map]
  (ps/render-file name (into map (assoc
                                   (environment)
                                   :sel id
                                   :custom-nav (nav/get-nav)))))