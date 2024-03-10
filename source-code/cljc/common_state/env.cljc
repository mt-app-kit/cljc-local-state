
(ns common-state.env
    (:require [common-state.state :as state]
              [common-state.utils :as utils]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-cursor
  ; @description
  ; Returns a Reagent cursor object that points to a specific state (in the 'COMMON-STATE' atom).
  ;
  ; @param (*) context-id
  ; @param (*) state-id
  ;
  ; @usage
  ; (get-cursor :my-context :my-state)
  ; =>
  ; #object[reagent.core/cursor]
  ;
  ; @return (cursor)
  [context-id state-id]
  (or (-> state/CURSORS deref (get-in [context-id state-id]))
      (when-let [cursor (utils/cursor state/COMMON-STATE [context-id state-id])]
                (->> cursor (swap! state/CURSORS assoc-in [context-id state-id]))
                (->> cursor))))

(defn get-state
  ; @description
  ; Returns a specific state optionally filtered to a nested value.
  ;
  ; @param (*) context-id
  ; @param (*) state-id
  ; @param (list of *)(opt) keys
  ;
  ; @usage
  ; (get-state :my-context :my-state)
  ; =>
  ; {:my-key "My value"}
  ;
  ; @usage
  ; (get-state :my-context :my-state :my-key)
  ; =>
  ; "My value"
  ;
  ; @return (*)
  [context-id state-id & keys]
  (if-let [cursor (get-cursor context-id state-id)]
          (-> cursor             deref (get-in keys))
          (-> state/COMMON-STATE deref (get-in [context-id state-id])
                                       (get-in keys))))
