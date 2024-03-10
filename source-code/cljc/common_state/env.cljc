
(ns common-state.env
    (:require [common-state.state :as state]
              [common-state.utils :as utils]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-cursor
  ; @description
  ; Returns a cursor that points to a specific state (in the 'COMMON-STATE' atom).
  ;
  ; @param (keyword) state-id
  ;
  ; @usage
  ; (get-cursor :my-state)
  ; =>
  ; #object[clojure.core.cursor]
  ;
  ; @return (cursor)
  [state-id]
  (if-let [cursor (-> state/CURSORS deref (get state-id))]
          (-> cursor)
          (let [cursor (utils/cursor state/COMMON-STATE [state-id])]
               (->> cursor (swap! state/CURSORS assoc state-id))
               (->> cursor))))

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
  (let [cursor (get-cursor state-id)]
       (-> cursor deref (get-in keys))))
