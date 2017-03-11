(ns xiaodai.blogure.nav)

(defrecord Nav [id name path])

(def ^:private default-nav
  [(Nav. 0 "主页" "/")
   (Nav. 1 "文章" "/article")
   (Nav. 2 "测试" "http://baidu.com")])

(defn get-nav [] default-nav)