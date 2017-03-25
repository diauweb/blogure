(ns xiaodai.blogure.article
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]
            [markdown.core :as md]
            [clojure.tools.logging :as log]
            [clojure.string :as string])
  (:import (clojure.lang Ref)
           java.io.File
           (java.util Date)))

(defrecord ArticleMeta [timestamp title])
(defrecord ArticleCacheItem [path title ^Long timestamp summary])

(def page-cache (atom []))
(def ^:const metapath "articles/metadata.json")

(when-not (.exists (io/file metapath))
  (io/make-parents (io/file metapath))
  (spit metapath "{}"))

;; The private things ;;

(defn- no-prefix [^String name]
  (.substring name 0 (.lastIndexOf name ".")))

(defn- read-meta [] (json/read-str (slurp metapath) :key-fn keyword))

(defn- has-meta? [^String id]
  (or ((keyword id) (read-meta))))

(defn- update-meta! [^String id map]
  (let [kw (keyword id)]
    (spit metapath (json/write-str (assoc (read-meta) kw map)))))

(defn- add-default-meta! [^File x]
  (let [n #(no-prefix (.getName %))]
    (update-meta! (n x) (ArticleMeta. (.lastModified x) (n x)))))

(defn- update-all-meta! []
  (spit metapath "{}")
  (let [ow (filter (fn [x] (-> (.getName x) (.endsWith ".md")))
                   (.listFiles (io/file "articles")))
        npf #(no-prefix (.getName %))]
    (doseq [x ow] (add-default-meta! x))))

(defn- raw-article-byid [^String x]
  (slurp (str "articles/" x ".md")))

(defn- get-summary [^String x]
  (with-open [r (io/reader (str "articles/" x ".md"))]
    (let [x1 (doall (take 5 (line-seq r)))
          x2 (reduce str x1)
          html (md/md-to-html-string x2)]
      (string/replace html #"<[^>]*>" " "))))

;; The public parts ;;
(defn add-new-meta! []
  (let [ow (filter (fn [x] (-> (.getName x) (.endsWith ".md")))
                   (.listFiles (io/file "articles")))
        npf #(no-prefix (.getName %))
        nw (filter (fn [x] (not (has-meta? (npf x)))) ow)]
    (doseq [x nw] (add-default-meta! x))))

(defn get-meta-byid [^String id]
  ((keyword id) (read-meta)))

(defn has-file? [^String id]
  (-> (io/file (str "articles/" id ".md")) .exists))

(defn get-html-article [^String id]
  (md/md-to-html-string (raw-article-byid id)))

(defn flush-cache []
  (log/info "Flushing Cache.. That may take a while.")
  (reset! page-cache [])
  (doseq [[k v] (read-meta)]
    (swap! page-cache conj
           (ArticleCacheItem. (name k) (:title v) (:timestamp v) (future (get-summary (name k))))))
  (swap! page-cache
         #(sort (fn [x y] (- (:timestamp y) (:timestamp x))) %)))



