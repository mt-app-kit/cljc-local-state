
(ns common-state.utils
    (:refer-clojure :exclude [atom])
    (:require [reagent.core]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn atom
  ; @description
  ; Returns a Clojure atom object or a Reagent atom object, depending on whether it is called from a Clojure or a ClojureScript namespace.
  ;
  ; @param (*) v
  ;
  ; @usage
  ; (atom "My initial value")
  ; =>
  ; #object[reagent.core/atom]
  ;
  ; @return (atom)
  [v]
  #?(:clj  (clojure.core/atom v)
     :cljs (reagent.core/atom v)))

(defn cursor
  ; @description
  ; Returns a Reagent cursor object, in case it is called from a ClojureScript namespace.
  ;
  ; @param (atom) a
  ; @param (vector) ks
  ;
  ; @usage
  ; (cursor MY-ATOM [:my-key])
  ; =>
  ; #object[reagent.core/cursor]
  ;
  ; @return (cursor)
  [a ks]
  #?(:cljs (reagent.core/cursor a ks)))
