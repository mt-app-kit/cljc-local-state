
(ns common-state.side-effects
    (:require [fruits.map.api    :refer [dissoc-in]]
              [fruits.vector.api :as vector]
              [common-state.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn update-state!
  ; @description
  ; Updates a specific state (within the 'COMMON-STATE' atom) with the given 'f' function.
  ;
  ; @param (*) context-id
  ; @param (*) state-id
  ; @param (function) f
  ; @param (list of *)(opt) params
  ;
  ; @usage
  ; (update-state! :my-context :my-state merge {...})
  [context-id state-id f & params]
  (letfn [(f0 [%] (apply f % params))]
         (swap! state/COMMON-STATE update-in [context-id state-id] f0)))

(defn assoc-state!
  ; @description
  ; Associates the given value as a specific state or optionally its nested value (within the 'COMMON-STATE' atom).
  ;
  ; @param (*) context-id
  ; @param (*) state-id
  ; @param (list of *)(opt) keys
  ; @param (*) value
  ;
  ; @usage
  ; (assoc-state! :my-context :my-state "My value")
  ;
  ; @usage
  ; (assoc-state! :my-context :my-state :my-key "My value")
  [context-id state-id & ksnv]
  (swap! state/COMMON-STATE assoc-in (-> ksnv (vector/remove-last-item)
                                              (vector/cons-item state-id context-id))
                                     (-> ksnv (vector/last-item))))

(defn dissoc-state!
  ; @description
  ; Dissociates a specific state or optionally its nested value (within the 'COMMON-STATE' atom).
  ;
  ; @param (*) context-id
  ; @param (*) state-id
  ; @param (list of *)(opt) keys
  ;
  ; @usage
  ; (dissoc-state! :my-context :my-state)
  ;
  ; @usage
  ; (dissoc-state! :my-context :my-state :my-key)
  [context-id state-id & keys]
  (swap! state/COMMON-STATE dissoc-in (vector/cons-item keys state-id context-id)))
