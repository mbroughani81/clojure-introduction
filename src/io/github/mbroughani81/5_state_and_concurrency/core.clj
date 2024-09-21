(ns io.github.mbroughani81.5-state-and-concurrency.core)


(comment
  ;; Promise
  (def result (promise))
  result
  (deliver result "salam")
  @result
  (deref result)
  ;; ---------------------------------------- ;;
  ;; Future
  (def result
    (let [p (promise)]
      (let [angieslist  "https://angieslist.com"
            homeadvisor "https://homeadvisor.com"]
        (doseq [url [angieslist homeadvisor]]
          (future (let [response (slurp url)]
                    (deliver p response)))))
      @p))
  result
  ;; ---------------------------------------- ;;
  )
