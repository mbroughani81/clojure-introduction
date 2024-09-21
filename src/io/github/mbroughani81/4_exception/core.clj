(ns io.github.mbroughani81.4-exception.core)

(defn find-book [books book-id]
  (let [-fn-         #(= (:id %) book-id)
        result       (filter -fn- books)
        result-count (count result)]
    (if (> result-count 0)
      (first result)
      (throw (ex-info "Book Not Found!" {:books   books
                                         :book-id book-id})))))

(comment
  (def books [{:id   :book1
               :name "book1"}
              {:id   :book2
               :name "book2"}
              {:id   :book3
               :name "book3"}])
  (find-book books :book1)

  (find-book books :book4)
  ;; ---------------------------------------- ;;
  (try
    (find-book books :book4)
    (catch Exception e
      (println "Got an exception => " (ex-message e))))
  ;; ---------------------------------------- ;;
  )
