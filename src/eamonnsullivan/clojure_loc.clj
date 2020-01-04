(ns eamonnsullivan.clojure-loc
  (:require [clojure.java.io :refer [reader]]
            [clojure.string :as string]
            [clojure.tools.cli :refer [parse-opts]])
  (:import java.io.File)
  (:gen-class))

(defn non-blank?
  "Does this line contain anything?"
  [line]
  (not (string/blank? line)))

(defn has-extension?
  "Does this file end with the extension?"
  [ext file]
  (.endsWith (str file) ext))

(def clojure-source? (partial has-extension? ".clj"))
(def clojurescript-source? (partial has-extension? ".cljs"))

(defn clojure-loc
  "How many non-blank lines of clojure code in this directory tree?"
  [start verbose]
  (reduce
   +
   (for [file (file-seq start)
         :when (or (clojure-source? file) (clojurescript-source? file))]
     (with-open [rdr (reader file)]
       (when verbose
         (println (.getPath file)))
       (count (filter non-blank? (line-seq rdr)))))))

(defn usage [options-summary]
  (string/join \newline
               ["Count the number of non-blank lines of clojure"
                "or clojurescript code in the current or specified"
                "directory and subdirectories."
                ""
                "Usage: program-name [options] starting-directory"
                ""
                "Options:"
                options-summary
                ""
                "If no directory is specified, then the current directory"
                "is assumed."]))

(def cli-options
  [["-h" "--help"]
   ["-v" "--verbose"]])

(defn -main
  "Print the total lines of (non-blank) clojure or clojurescript code in
  the specified directory tree."
  [& args]
  (let [{:keys [options arguments summary]} (parse-opts args cli-options)
        dir (if (zero? (count arguments)) "." (first arguments))]
    (cond
      (:help options) (println (usage summary))
      :else (println "total lines:" (clojure-loc (File. dir) (:verbose options))))))
