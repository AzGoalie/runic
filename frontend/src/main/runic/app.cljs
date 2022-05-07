(ns runic.app
  (:require
   ["@babylonjs/core/Engines/engine" :refer (Engine)]
   ["@babylonjs/core/scene" :refer (Scene)]
   ["@babylonjs/core/Maths/math" :refer (Vector3)]
   ["@babylonjs/core/Cameras/arcRotateCamera" :refer (ArcRotateCamera)]))

(println "In App")

(defn aaa [] (println "aaa"))

(defn initialize-app [canvasId]
  (let [canvas (.getElementById js/document canvasId)
        engine (Engine. canvas)
        scene (Scene. engine)
        camera (ArcRotateCamera. "Camera" (/ Math/PI 2) (/ Math/PI 2.5) 10 (.Zero Vector3) scene)]
    (set! (.-upperBetaLimit camera) (/ Math/PI 2.2))
    (set! (.-upperRadiusLimit camera) 20)
    (set! (.-lowerRadiusLimit camera) 3)
    (.attachControl camera canvas true)

    {:canvas canvas
     :engine engine
     :scene scene
     :camera camera}))