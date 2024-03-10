
(ns common-state.utils
    (:refer-clojure :exclude [atom cursor])
    (:require [reagent.core]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn atom
  ; @description
  ; Returns a Clojure atom / Reagent atom object depends on whether it is called from a Clojure or a ClojureScript namespace.
  ;
  ; @param (*) v
  ;
  ; @usage
  ; (atom "My initial value")
  ; =>
  ; #object[clojure.core.atom]
  ;
  ; @return (atom)
  [v]
  #?(:clj  (clojure.core/atom v)
     :cljs (reagent.core/atom v)))

(defn cursor
  ; @description
  ; Returns a Clojure cursor / Reagent cursor object depends on whether it is called from a Clojure or a ClojureScript namespace.
  ;
  ; @param (atom) a
  ; @param (vector) ks
  ;
  ; @usage
  ; (cursor MY-ATOM [:my-key])
  ; =>
  ; #object[clojure.core.cursor]
  ;
  ; @return (cursor)
  [a ks]
  #?(:clj  (clojure.core/cursor a ks)
     :cljs (reagent.core/cursor a ks)))
