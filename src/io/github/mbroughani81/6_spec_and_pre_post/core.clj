(ns io.github.mbroughani81.6-spec-and-pre-post.core
  (:require
   [clojure.spec.alpha :as spec]
   [io.github.mbroughani81.3-record-and-protocol.core :as core]
   [io.github.mbroughani81.3-record-and-protocol.proto :as proto]))

(spec/def ::items (spec/coll-of core/is-item?))

(defn <-items-available:balance [items buyer]
  {:pre [(spec/valid? ::items items)
         (core/is-buyer? buyer)]}
  (filter (fn [{:keys [price]}]
            (< price (:balance buyer)))
          items))

(comment
  (def all-items [(core/->Item "item-id-1" "item1" 1000)
                  (core/->Item "item-id-1" "item1" 2000)
                  (core/->Item "item-id-1" "item1" 3000)
                  ])
  (def buyer (core/cons-user "ali123132" "buyer"))
  (def buyer (proto/charge-account buyer 1500))
  (def seller (core/cons-user "ali123132" "seller"))
  (<-items-available:balance all-items buyer)
  (<-items-available:balance all-items seller)

  ;; ---------------------------------------- ;;
  )
