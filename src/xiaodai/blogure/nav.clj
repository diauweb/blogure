(ns xiaodai.blogure.nav
  (:require [xiaodai.blogure.config :as cfg]))

(defrecord Nav [order name path])

(def ^:private default-nav
  [(Nav. -2 "主页" "/")
   (Nav. -1 "文章" "#")])

(defn get-nav [] (into default-nav (-> "cfg/main.cfg.json" cfg/read-cfg :custom-tab)))
