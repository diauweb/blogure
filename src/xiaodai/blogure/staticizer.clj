(ns xiaodai.blogure.staticizer
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]
            [xiaodai.blogure.core :as blogure-core]
            [xiaodai.blogure.article :as article]
            [xiaodai.blogure.render :as r]))


(def ^:const git-path "staticize/blogure-info")

;Looking for a solution!
(def ^:const ^:private reslist
  ["static/css/style.css"
   "static/image/logo.png"
   "static/image/logo@40px.png"])

(defn- copy-res [] (doseq [a reslist] ((io/make-parents (str "staticize/" a)
                                        (spit (io/file  (str "staticize/" a))
                                              (slurp (io/resource (str "webroot/public/" a))))) reslist)))


(defn gen-github-page []
  (when-not (.exists (io/file git-path))
    (io/make-parents (io/file git-path)))
  (spit git-path (json/write-str
                   {:version blogure-core/version
                    :lastUpdate (System/currentTimeMillis)
                    :magic 42
                    :generator "Blogure"}))

  #_"Ensure newest cache" (article/add-new-meta!) (article/flush-cache)
  #_"Resources" (io/copy (io/resource "webroot/public") (io/file "staticize/"))
  #_"Static pages"
  (spit "staticize/index.html"
        (r/render-file "templates/index.html" -2 {:article-list @article/page-cache})))






