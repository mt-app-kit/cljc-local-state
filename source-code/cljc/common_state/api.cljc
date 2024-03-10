
(ns common-state.api
    (:refer-clojure :exclude [atom])
    (:require [common-state.env          :as env]
              [common-state.side-effects :as side-effects]
              [common-state.state        :as state]
              [common-state.utils        :as utils]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial Common state
;
; This library implements a shared state managed by a common atom.
; Each independent state within this common atom is accessed using separate cursors,
; which helps avoid unintended reactions from subscriptions of other states.

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (common-state.env/*)
(def get-cursor env/get-cursor)
(def get-state  env/get-state)

; @redirect (common-state.side-effects/*)
(def update-state! side-effects/update-state!)
(def assoc-state!  side-effects/assoc-state!)
(def dissoc-state! side-effects/dissoc-state!)

; @redirect (common-state.state/*)
(def COMMON-STATE state/COMMON-STATE)
(def CURSORS      state/CURSORS)

; @redirect (common-state.state/*)
(def atom   utils/atom)
(def cursor utils/cursor)
