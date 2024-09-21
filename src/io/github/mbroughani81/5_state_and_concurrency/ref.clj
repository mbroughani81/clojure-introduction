(ns io.github.mbroughani81.5-state-and-concurrency.ref)

(defn transfer [from to amount]
  (dosync
    (alter from - amount)
    (alter to + amount)))

(defn stress-thread [from to iterations amount]
  (Thread. #(dotimes [_ iterations] (transfer from to amount))))

(def ali-balance (ref 1000))
(def amin-balance (ref 1000))

(comment
  (transfer ali-balance amin-balance 100)

  (let [t1 (stress-thread ali-balance amin-balance 100 2)
        t2 (stress-thread amin-balance ali-balance 101 2)]
    (.start t1)
    (.start t2)
    (.join t1)
    (.join t2))


  @ali-balance
  @amin-balance

  )
