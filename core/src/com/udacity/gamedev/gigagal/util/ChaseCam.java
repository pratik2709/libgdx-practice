package com.udacity.gamedev.gigagal.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.udacity.gamedev.gigagal.entities.GigaGal;

import static com.badlogic.gdx.Input.Keys.*;

public class ChaseCam {
    Camera camera;
    GigaGal gigaGalPlayer;
    Boolean following;

    public ChaseCam(Camera camera, GigaGal gigaGal) {
        this.camera = camera;
        this.gigaGalPlayer = gigaGal;
        following = true;
    }

    public void update(float delta) {

        if (Gdx.input.isKeyPressed(SPACE)) {
            following = ! following;
        }

        if(following){
            camera.position.x = gigaGalPlayer.gigagalPosition.x;
            camera.position.y = gigaGalPlayer.gigagalPosition.y;
        }
        else{
            if(Gdx.input.isKeyPressed(A)){
                camera.position.x -= delta * Constants.CAMERA_MOVE_SPEED;
            }
            else if(Gdx.input.isKeyPressed(D)){
                camera.position.x += delta * Constants.CAMERA_MOVE_SPEED;
            }
            else if(Gdx.input.isKeyPressed(W)){
                camera.position.y += delta * Constants.CAMERA_MOVE_SPEED;
            }
            else if(Gdx.input.isKeyPressed(S)){
                camera.position.y -= delta * Constants.CAMERA_MOVE_SPEED;
            }
        }

    }
}
