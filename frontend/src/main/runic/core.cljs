(ns runic.core
  (:require [shadow.loader :as loader]
            [runic.app :refer [initialize-app]]
            [lambdaisland.glogi :as log]
            [lambdaisland.glogi.console :as glogi-console]))

(glogi-console/install!)
(log/set-levels {"goog" :off
                 'runic :debug})

(log/debug :msg "Initializing BabylonJS")

(def ctx (initialize-app "gameCanvas"))
(.runRenderLoop (:engine ctx) #(.render (:scene ctx)))
(.addEventListener js/window "resize" #(.resize (:engine ctx)))

(when goog/DEBUG
  (log/debug :msg "Attaching the inspector")
  (-> (loader/load "inspector")
      (.then #((resolve 'runic.dev.inspector/attach) (:scene ctx))
             #(log/error :msg "Failed to load the inspector")))

  (log/debug :msg "Loading the test scene")
  (-> (loader/load "test-scene")
      (.then #((resolve 'runic.dev.scene/create-test-scene) ctx)
             #(log/error :msg "Failed to load the test scene"))))
