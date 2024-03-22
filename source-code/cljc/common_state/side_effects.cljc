
(ns common-state.side-effects
    (:require [common-state.state :as state]
              [fruits.map.api     :refer [dissoc-in]]
              [fruits.vector.api  :as vector]))

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
         (let [state (-> state/COMMON-STATE deref (get-in [context-id state-id]))
               state (-> state f0)]
              (if (-> state nil?)
                  (-> state/COMMON-STATE (swap! dissoc-in [context-id state-id]))
                  (-> state/COMMON-STATE (swap! assoc-in  [context-id state-id] state))))))

(defn assoc-state!
  ; @description
  ; Associates the given value as a specific state or optionally its nested value (within the 'COMMON-STATE' atom).
  ;
  ; @param (*) context-id
  ; @param (*) state-id
  ; @param (list of *)(opt) ks
  ; @param (*) v
  ;
  ; @usage
  ; (assoc-state! :my-context :my-state "My value")
  ;
  ; @usage
  ; (assoc-state! :my-context :my-state :my-key :my-nested-key ... "My value")
  [context-id state-id & ksnv]
  (let [ks (-> ksnv (vector/remove-last-item))
        ks (-> ks   (vector/cons-item state-id context-id))
        v  (-> ksnv (vector/last-item))]
       (if (-> v nil?)
           (-> state/COMMON-STATE (swap! dissoc-in ks))
           (-> state/COMMON-STATE (swap! assoc-in  ks v)))))

(defn dissoc-state!
  ; @description
  ; Dissociates a specific state or optionally its nested value (within the 'COMMON-STATE' atom).
  ;
  ; @param (*) context-id
  ; @param (*) state-id
  ; @param (list of *)(opt) ks
  ;
  ; @usage
  ; (dissoc-state! :my-context :my-state)
  ;
  ; @usage
  ; (dissoc-state! :my-context :my-state :my-key :my-nested-key ...)
  [context-id state-id & ks]
  (let [ks (vector/cons-item ks state-id context-id)]
       (-> state/COMMON-STATE (swap! dissoc-in ks))))
