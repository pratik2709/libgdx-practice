package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Enums;
import com.udacity.gamedev.gigagal.util.Util;

public class Bullet {
    private final Enums.Direction direction;
    private Vector2 position;

    public Bullet(Vector2 position, Enums.Direction direction){
        this.position = position;
        this.direction = direction;

    }

    public void update(float delta){
        switch (direction){
            case LEFT:
                position.x -= delta * Constants.BULLET_MOVE_SPEED;
                break;
            case RIGHT:
                position.x += delta * Constants.BULLET_MOVE_SPEED;
        }
    }

    public void render(SpriteBatch batch){
        //render function
        Util.drawTextureRegion(batch, Assets.instance.bulletAssets.bulletRegion, position,
                Constants.BULLET_CENTER);
    }
}
