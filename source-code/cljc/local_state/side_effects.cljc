
(ns local-state.side-effects
    (:require [local-state.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn update-state!
  ; @description
  ; Updates a specific state (in the 'STATES' atom) with the given 'f' function.
  ;
  ; @param (keyword) state-id
  ; @param (function) f
  ; @param (list of *)(opt) params
  ;
  ; @usage
  ; (update-state! :my-state merge {...})
  [state-id f & params]
  (letfn [(f0 [%] (apply f % params))]
         (swap! state/STATES update state-id f0)))

(defn clear-state!
  ; @description
  ; Removes a specific state (from the 'STATES' atom).
  ;
  ; @param (keyword) state-id
  ;
  ; @usage
  ; (clear-state! :my-state)
  [state-id]
  (swap! state/STATES dissoc state-id))
