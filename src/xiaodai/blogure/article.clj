(ns xiaodai.blogure.article
  (:require [markdown.core :as md]
            [clojure.java.io :as io]
            [xiaodai.blogure.config :as cfg]
            [clojure.data.json :as json]))

(def ^:const metapath "articles/metadata.json")

;this clears! dangerous
(defn- new-meta-file! []
  (spit metapath "{}"))

(defn- getmeta [] (json/read-str (slurp metapath) :key-fn keyword))

(let [f (io/file metapath)]
  (when-not (.exists f) (new-meta-file!)))

(defn- raw-article-byid [^String x]
  (slurp (str "articles/" x ".md")))

(defn get-html-article [^String article-id]
  (md/md-to-html-string (raw-article-byid article-id)))


(defn update-meta! [^String id map]
  (let [kw (keyword id)]
    (spit metapath (json/write-str
                     (assoc (getmeta) kw map)))))

(defn has-meta? [^String id]
  (true? ((keyword id) (getmeta))))

(defn- no-prefix [^String name]
  (.substring name 0 (.lastIndexOf name ".")))

; DANGEROUS! Please, do not use
(defn update-all-meta! []
  (let [f (io/file metapath)]
    (when (.exists f) (new-meta-file!)))
  (let [ow (filter (fn [x] (-> (.getName x) (.endsWith ".md")))
                   (.listFiles (io/file "articles")))]
    (apply (fn [x] (update-meta! (-> (.getName x) no-prefix) {})) ow)))

(defn add-new-meta! []
  (let [ow (filter (fn [x] (-> (.getName x) (.endsWith ".md")))
                   (.listFiles (io/file "articles")))]
    (apply (fn [x]
             (let [name (-> (.getName x) no-prefix)]
               (when-not (has-meta? name) (update-meta! name {})))) ow)))

(defn has-article? [^String article-byid]
  (-> (io/file (str "articles/" article-byid ".md")) .exists))

(defn article-list [] (getmeta))