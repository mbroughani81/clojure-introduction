(ns io.github.mbroughani81.5-state-and-concurrency.atom)

(defn update-status [counter ip]
  (swap! counter (fn [{:keys [cnt ips]}]
                   {:cnt (inc cnt)
                    :ips (conj ips ip)}
                   )))

(defn stress-thread [counter iterations ip]
  (Thread. #(dotimes [_ iterations] (update-status counter ip))))

(def visit-status (atom {:cnt 0
                         :ips #{}}))

(comment
  (let [t1 (stress-thread visit-status 100 "ip1")
        t2 (stress-thread visit-status 200 "ip2")]
    (.start t1)
    (.start t2)
    (.join t1)
    (.join t2))


  @visit-status


  )
