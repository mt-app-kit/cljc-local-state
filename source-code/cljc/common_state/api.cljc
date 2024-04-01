
(ns common-state.api
    (:refer-clojure :exclude [atom])
    (:require [common-state.env          :as env]
              [common-state.side-effects :as side-effects]
              [common-state.state        :as state]
              [common-state.utils        :as utils]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial Common State
;
; This library implements a shared state managed by a common atom.
; Each independent state within this common atom is accessed using separate cursors,
; which helps avoid unintended reactions from subscriptions of other states.

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial Demo
;
; @usage
; ;; Updating different states doesn't interfere with other states.
; ;; Subscribing to a specific state reacts only changes of that particular state.
; (assoc-state! :my-context :my-state      {:my-key      "My value"})
; (assoc-state! :my-context :another-state {:another-key "Another value"})
;
; @usage
; ;; This component re-renders only when the ':my-context / :my-state' changes.
; (defn my-ui
;   []
;   [:div "My state:" (get-state :my-context :my-state)])
; =>
; [:div "My state:" {:my-key "My value"}]
;
; ;; This component re-renders only when the ':my-context / :another-state' changes.
; (defn another-ui
;   []
;   [:div "Another state:" (get-state :my-context :another-state)])
; =>
; [:div "Another state:" {:another-key "Another value"}]

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

; @redirect (common-state.utils/*)
(def atom   utils/atom)
(def cursor utils/cursor)
