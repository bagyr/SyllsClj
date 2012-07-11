(ns SyllsClj.file
  (:require 
    [SyllsClj.core :as core]
    [clojure.java.io :as io]
    [clojure.string :as string]))

; (defn sent-seq [text]
;   )

; (defn sent? [text]
;   )

(defn procString
  ([text]
    (let [sent (string/split text #"(?<=[.,;])")]
      (map string/trim sent)
      )
    )
  ([text dnf]
    (let [sent (string/split text #"(?<=[.,;])")
          prepSent (map string/trim sent)]
      (cons
        (str dnf
          (first prepSent))
        (rest prepSent)
        )
      )
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