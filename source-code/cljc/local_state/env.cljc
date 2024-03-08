
(ns local-state.env
    (:require [fruits.vector.api :as vector]
              [local-state.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-state
  ; @description
  ; Returns a specific state optionally filtered to a nested value.
  ;
  ; @param (keyword) state-id
  ; @param (list of *)(opt) keys
  ;
  ; @usage
  ; (get-state :my-state)
  ; =>
  ; {:my-key "My value"}
  ;
  ; @usage
  ; (get-state :my-state :my-key)
  ; =>
  ; "My value"
  ;
  ; @return (*)
  [state-id & keys]
  (get-in @state/STATES (vector/cons-item keys state-id)))
