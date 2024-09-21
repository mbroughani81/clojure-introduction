(ns io.github.mbroughani81.2-function.core
  (:require
   [taoensso.timbre :as timbre]))

(def pi Math/PI)

(defn cal-circum [r]
  (* 2 pi r))

(defn cal-area [r]
  (* pi r r))

(defn add [x y]
  (+ x y))

(defn log-component-starting [comp-name msg]
  (timbre/info "Starting component " comp-name ": " msg))

(comment
  (+ 1 2)

  (* 2 3)

  ((fn [x]
     (* x x))
   3)

  ;; ---------------------------------------- ;;
  pi
  (cal-circum 1)
  (cal-area 1)
  ;; ---------------------------------------- ;;
  ;; Let
  (let [d 4
        r (/ d 2)])
  ;; ---------------------------------------- ;;
  ;; If
  (if (not= pi pi)
    pi
    -1)
  ;; ---------------------------------------- ;;
  ;; Loop
  (loop [sum 0
         x   1]
    (if (< x 5)
      (recur (+ sum x)
             (inc x))
      sum))
  ;; ---------------------------------------- ;;
  )
