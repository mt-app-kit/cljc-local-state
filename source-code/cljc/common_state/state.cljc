
(ns common-state.state
    (:require [common-state.utils :as utils]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @description
; Stored common state.
;
; @atom (map)
; {:my-state (map)}
;
; @usage
; (deref COMMON-STATE)
; =>
; {:my-state {:my-key "My value"}}
(def COMMON-STATE (utils/atom {}))

; @description
; Stored cursors for states.
;
; @atom (map)
; {:my-state (cursor)}
;
; @usage
; (deref CURSORS)
; =>
; {:my-state #object[clojure.core.cursor]}
(def CURSORS (utils/atom {}))
