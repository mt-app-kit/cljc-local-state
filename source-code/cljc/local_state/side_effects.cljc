
(ns local-state.side-effects
    (:require [local-state.state :as state]
              [fruits.map.api :refer [dissoc-in]]
              [fruits.vector.api :as vector]))

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

(defn assoc-state!
  ; @description
  ; Associates the given value as a specific state or optionally its nested value (in the 'STATES' atom).
  ;
  ; @param (keyword) state-id
  ; @param (list of *)(opt) keys
  ; @param (*) value
  ;
  ; @usage
  ; (assoc-state! :my-state "My value")
  ;
  ; @usage
  ; (assoc-state! :my-state :my-key "My value")
  [state-id & ksnv]
  (swap! state/STATES assoc-in (-> ksnv (vector/remove-last-item)
                                        (vector/cons-item state-id))
                               (-> ksnv (vector/last-item))))

(defn dissoc-state!
  ; @description
  ; Dissociates a specific state or optionally its nested value (from the 'STATES' atom).
  ;
  ; @param (keyword) state-id
  ; @param (list of *)(opt) keys
  ;
  ; @usage
  ; (dissoc-state! :my-state)
  ;
  ; @usage
  ; (dissoc-state! :my-state :my-key)
  [state-id & keys]
  (swap! state/STATES dissoc-in (vector/cons-item keys state-id)))
