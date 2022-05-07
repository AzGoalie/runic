(ns runic.dev.inspector
  (:require [lambdaisland.glogi :as log]
            ["@babylonjs/core/Legacy/legacy"] ; The inspector es6 module is all messed up without this
            ["@babylonjs/core/Debug/debugLayer"]
            ["@babylonjs/inspector"]))

(log/trace :msg "In inspector")

(defn attach [^Scene scene]
  (.addEventListener
   js/window
   "keydown"
   #(when (and (.-shiftKey %) (.-altKey %) (.-ctrlKey %) (= (.-code %) "KeyI"))
      (if (.debugLayer.isVisible scene)
        (.debugLayer.hide scene)
        (.debugLayer.show scene)))))