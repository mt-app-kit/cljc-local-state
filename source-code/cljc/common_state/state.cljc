
(ns common-state.state
    (:require [common-state.utils :as utils]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @description
; Stored common state.
;
; @atom (map)
; {:my-context (map)
;   {:my-state (*)}}
;
; @usage
; (deref COMMON-STATE)
; =>
; {:my-context {:my-state {:my-key "My value"}}}
(def COMMON-STATE (utils/atom {}))

; @description
; Stored cursors of states.
;
; @atom (map)
; {:my-context (map)
;   {:my-state (cursor)}}
;
; @usage
; (deref CURSORS)
; =>
; {:my-context {:my-state #object[reagent.core/cursor]}}
(def CURSORS (utils/atom {}))
