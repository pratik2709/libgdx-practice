package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.Level;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Enums;
import com.udacity.gamedev.gigagal.util.Util;

public class Bullet {
    private final Enums.Direction direction;
    private Vector2 position;
    private final Level level;
    private Boolean bulletActive;

    public Bullet(Level level, Vector2 position, Enums.Direction direction){
        this.level = level;
        this.position = position;
        this.direction = direction;
        bulletActive = true;

    }

    public void update(float delta){
        switch (direction){
            case LEFT:
                position.x -= delta * Constants.BULLET_MOVE_SPEED;
                break;
            case RIGHT:
                position.x += delta * Constants.BULLET_MOVE_SPEED;
                break;
        }

        float worldWidth = level.getViewport().getWorldWidth();
        float cameraHorizontalPosition = level.getViewport().getCamera().position.x;
        //??
        if(position.x > cameraHorizontalPosition + worldWidth/2 ||
                position.x < cameraHorizontalPosition - worldWidth/2){
            bulletActive = false;
        }
    }

    public void render(SpriteBatch batch){
        //render function
        Util.drawTextureRegion(batch, Assets.instance.bulletAssets.bulletRegion, position,
                Constants.BULLET_CENTER);
    }

    public Boolean getBulletActive() {
        return bulletActive;
    }
}
