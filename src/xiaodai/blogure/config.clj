(ns xiaodai.blogure.config
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log])
  (:import (java.io File)))

(defn- ^String no-comment [^String meta]
  (.replaceAll meta "/\\*{1,2}[\\s\\S]*?\\*/" ""))

; Ensure folder exists first.
(io/make-parents (io/file "cfg/main.cfg.json"))

(defn- gen-cfg [^Boolean parse-fail? path]
  (if parse-fail? (log/warn "Configureation seens to be broken, generating new one...")
                  (log/warn "Config does not exist, Generating new config files...."))
  (with-open [r (io/reader (io/resource path))
              w (io/writer path)]
    (io/copy r w)))

(defn- direct-read-cfg [path]
  (json/read-str (-> path slurp no-comment) :key-fn keyword))

(defn read-cfg [path]
  (try (direct-read-cfg path)
       (catch Exception e
         (if (.exists (io/file path))
           (let [nf (str path ".bak")]
             (with-open [r (io/reader path)
                         w (io/writer nf)]
               (.createNewFile (io/file nf))
               (io/copy r w))
             (gen-cfg true path))
           (gen-cfg false path))
         (direct-read-cfg path))))

; Ensure file EXIST when using this function!
(defn save-cfg [cfg-map path]
  (spit path (json/write-str cfg-map)))

