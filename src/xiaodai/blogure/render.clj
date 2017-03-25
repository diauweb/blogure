(ns xiaodai.blogure.render
  (:require [xiaodai.blogure.config :as cfg]
            [xiaodai.blogure.nav :as nav]
            [selmer.parser :as ps])
  (:import (java.util Date)))


(defn- environment []
  (-> "cfg/main.cfg.json" cfg/read-cfg :site-cfg))

(defn render-file [^String name ^Number id map]
  (let [env (environment)]
    (ps/render-file name (into map (assoc env
                                     :sel id
                                     :custom-nav (nav/get-nav))))))