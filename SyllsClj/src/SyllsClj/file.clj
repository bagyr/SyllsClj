(ns SyllsClj.file
  (:require 
    [SyllsClj.core :as core]
    [clojure.java.io :as io]
    [clojure.string :as string]))

; (defn sent-seq [text]
;   )

; (defn sent? [text]
;   )

(defn procString [str]
  (let [sent (string/split str #"(?<=[.,;])")]
    (map string/trim sent)
    )
  )

(defn readFile [file]
  (with-open [rdr (io/reader file)]
    (let [out ()]
      (doseq [line (line-seq rdr)]
          (procString line)
          )
      )
    )
  )

; (defn readSent [text]
  
;   )