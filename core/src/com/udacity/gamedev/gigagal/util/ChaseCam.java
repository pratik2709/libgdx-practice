package com.udacity.gamedev.gigagal.util;

import com.badlogic.gdx.graphics.Camera;
import com.udacity.gamedev.gigagal.entities.GigaGal;

public class ChaseCam {
    Camera camera;
    GigaGal gigaGalPlayer;

    public ChaseCam(Camera camera, GigaGal gigaGal) {
        this.camera = camera;
        this.gigaGalPlayer = gigaGal;
    }

    public void update() {
        camera.position.x = gigaGalPlayer.gigagalPosition.x;
        camera.position.y = gigaGalPlayer.gigagalPosition.y;
    }
}
