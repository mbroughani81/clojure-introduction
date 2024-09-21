(ns io.github.mbroughani81.3-record-and-protocol.core
  (:require
   [clojure.spec.alpha :as spec]
   [io.github.mbroughani81.3-record-and-protocol.proto :as proto]))


(def seller-role "seller")
(def buyer-role "buyer")

(def ROLES #{seller-role buyer-role})

;; ---------------------------------------- ;;

(defrecord Item [item-id name price])
(defn is-item? [x] (instance? Item x))

(defrecord Seller [username items]
  proto/Seller-Proto
  (add-item [this item]
    (assoc this :items (conj items item))))

(defrecord Buyer [username balance]
  proto/Buyer-Proto
  (can-buy? [this item]
    (>= balance (:price item)))
  (add-expense [this x]
    (assoc this :balance (- balance x)))
  (charge-account [this x]
    (assoc this :balance (+ balance x))))
(defn is-buyer? [x] (instance? Buyer x))

;; ---------------------------------------- ;;

(defn cons-user [username role]
  (let [role-ok?     (contains? ROLES role)
        is-seller?   (= seller-role role)
        is-buyer?    (= buyer-role role)
        username-ok? (> (count username)
                        6)
        ok?          (and role-ok? username-ok?)]
    (if ok?
      (cond
        is-seller? (map->Seller {:username username
                                 :items    []})
        is-buyer?  (map->Buyer {:username username
                                :balance  0}))
      {:role-ok?     role-ok?
       :username-ok? username-ok?})))


(comment
  (def item (map->Item {:item-id "item-id1"
                        :name    "item1"
                        :price   200}))

  (:k1 {:k1 "value1"
        :k2 "value2"})

  (keys item)

  (:item-id item)
  (:item-id {:item-id "item-id1"
             :name    "item1"
             :price   200})

  ;; ---------------------------------------- ;;
  (cons-user "ali123132" "seller")
  ;; ---------------------------------------- ;;
  (def u1 (cons-user "ABCD-Shop" "seller"))
  u1
  (:username u1)
  (:items u1)
  (def i1 (map->Item {:item-id "id1" :name "kala" :price 123}))
  (proto/add-item u1 i1)
  u1
  ;; ---------------------------------------- ;;
  (is-buyer? (cons-user "ali123132" "buyer"))
  ;; ---------------------------------------- ;;
  )
