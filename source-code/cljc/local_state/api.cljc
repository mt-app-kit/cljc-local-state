
(ns local-state.api
    (:require [local-state.env          :as env]
              [local-state.side-effects :as side-effects]
              [local-state.state        :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (local-state.env/*)
(def get-state env/get-state)

; @redirect (local-state.side-effects/*)
(def update-state! side-effects/update-state!)
(def assoc-state!  side-effects/assoc-state!)
(def dissoc-state! side-effects/dissoc-state!)

; @redirect (local-state.state/*)
(def STATES state/STATES)
