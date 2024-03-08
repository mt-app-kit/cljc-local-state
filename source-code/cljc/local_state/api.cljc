
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
(def clear-state!  side-effects/clear-state!)

; @redirect (local-state.state/*)
(def STATES state/STATES)
  
