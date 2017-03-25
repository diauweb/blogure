(ns xiaodai.blogure.route
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [xiaodai.blogure.article :as article]
            [clojure.java.io :as io]
            [clojure.tools.logging :as log])
  (:use [xiaodai.blogure.render]))

(defn- notfound []  (slurp (io/resource "webroot/public/404.html")))

(def last-flush (atom 0))

(defroutes route
           (GET "/" req (render-file "templates/index.html" -2 {:article-list @article/page-cache}))
           (GET "/article/:id" [id]
             (if (article/has-file? id)
               (render-file "templates/article.html" -1
                            {:content (article/get-html-article id)
                             :meta (article/get-meta-byid id)})
               (route/not-found (notfound))))
           (GET "/flush" req
             (if (> (- (System/currentTimeMillis) @last-flush) 3000)
               (do (article/add-new-meta!) (article/flush-cache)
                   (reset! last-flush (System/currentTimeMillis))
                   (log/info (str "Flushed Cache! Requester's map:" req))
                   "OK!")
               "Fuck Off! Flushing too quickly is bad for your kidney!"))
           (route/not-found (notfound)))