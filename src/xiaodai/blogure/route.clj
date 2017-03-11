(ns xiaodai.blogure.route
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [xiaodai.blogure.article :as article]
            [clojure.java.io :as io])
  (:use [xiaodai.blogure.render]))

(defn- notfound []  (slurp (io/resource "webroot/public/404.html")))

(defroutes route
           (GET "/" req (render-file "/templates/index.html" 0 {:article-list (article/article-list)}))
           (GET "/article/:id" [id]
             (if (article/has-article? id)
               (render-file "/templates/article.html" 1
                            {:content (article/get-html-article id)})
               (route/not-found (notfound))))
           (GET "/flush" req
             (article/add-new-meta!) "OK!")
           (route/not-found (notfound)))

