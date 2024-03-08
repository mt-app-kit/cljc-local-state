
(ns local-state.state
    (:require [reagent.core :as reagent]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @description
; Stored local states.
;
; @atom (map)
; {:my-state (map)}
;
; @usage
; (deref STATES)
; =>
; {:my-state {:my-key "My value"}}
(def STATES #?(:clj  (atom         {})
               :cljs (reagent/atom {})))
