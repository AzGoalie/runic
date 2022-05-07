(ns runic.dev.scene
  (:require ["@babylonjs/core/Maths/math" :refer (Vector3)]
            ["@babylonjs/core/Lights/hemisphericLight" :refer (HemisphericLight)]
            ["@babylonjs/core/Meshes/meshBuilder" :refer (MeshBuilder)]
            ["@babylonjs/materials/grid" :refer (GridMaterial)]))

(defn create-test-scene [{:keys [scene camera]}]
  (let [light (HemisphericLight. "light" (Vector3. 0 1 0) scene)
        gridMaterial (GridMaterial. "grid" scene)
        sphere (.CreateSphere MeshBuilder "sphere" #js {:segments 16 :diameter 2} scene)
        ground (.CreateGround MeshBuilder "ground" #js {:width 10 :height 10 :segments 2} scene)]
    (set! (.-intensity light) 0.7)
    (set! (.-position.y sphere) 2)
    (set! (.-material sphere) gridMaterial)
    (set! (.-lockedTarget camera) sphere)
    (set! (.-material ground) gridMaterial)))
