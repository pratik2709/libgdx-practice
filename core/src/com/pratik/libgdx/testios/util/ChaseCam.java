package com.pratik.libgdx.testios.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.pratik.libgdx.testios.entities.GigaGal;

import static com.badlogic.gdx.Input.Keys.*;

public class ChaseCam {
    Camera camera;
    GigaGal gigaGalPlayer;
    Boolean following;
    public Boolean setChasecam;
    public long shakeStartTime;
    public Enums.shakeState shakeState;

    float radius = 30.0f;
    float randomAngle = MathUtils.random() % 360;
//    Vector2 offset = new Vector2(MathUtils.sin(randomAngle) * radius, MathUtils.cos(randomAngle));
    public Vector2 offset = new Vector2(0, 0);

    public ChaseCam(Camera camera, GigaGal gigaGal) {
        this.camera = camera;
        this.gigaGalPlayer = gigaGal;
        following = true;
        setChasecam = false;
        shakeStartTime = 0;
        shakeState = Enums.shakeState.STOP;
    }

    public void update(float delta) {

        if (Gdx.input.isKeyPressed(SPACE)) {
            following = !following;
        }

        if (following) {
            camera.position.x = gigaGalPlayer.gigagalPosition.x + offset.x;
            camera.position.y = gigaGalPlayer.gigagalPosition.y + offset.y;
        } else {
            if (Gdx.input.isKeyPressed(A)) {
                camera.position.x -= delta * Constants.CAMERA_MOVE_SPEED;
            } else if (Gdx.input.isKeyPressed(D)) {
                camera.position.x += delta * Constants.CAMERA_MOVE_SPEED;
            } else if (Gdx.input.isKeyPressed(W)) {
                camera.position.y += delta * Constants.CAMERA_MOVE_SPEED;
            } else if (Gdx.input.isKeyPressed(S)) {
                camera.position.y -= delta * Constants.CAMERA_MOVE_SPEED;
            }

        }

    }

    public Vector2 shakeCamera() {
        radius *= 0.9;
        randomAngle += (180 + MathUtils.random() % 60);
        offset = new Vector2(MathUtils.sin(randomAngle) * radius, MathUtils.cos(randomAngle));
        return offset;
    }

    public void startShakeCam() {
        if (shakeState != Enums.shakeState.START && shakeState == Enums.shakeState.NEXT_READY) {
            shakeState = Enums.shakeState.START;
            shakeStartTime = TimeUtils.nanoTime();
        }
        if(shakeState == Enums.shakeState.START){
            shakeCamera();
        }
    }

    public void stopShakeCam() {
        float elapsed = Util.secondsSince(shakeStartTime);

        if (elapsed > Constants.SHAKE_DURATION) {
            offset.setZero();
            shakeStartTime = 0;
            shakeState = Enums.shakeState.STOP;
        }
    }
}
