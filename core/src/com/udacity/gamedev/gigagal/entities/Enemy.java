package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Enums.Direction;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Util;

import static java.lang.Math.PI;

public class Enemy {

    Platform platform;
    Vector2 enemyPosition;

    private Direction direction;
    final long startTime;


    public Enemy(Platform platform) {
        this.platform = platform;
        // initialize platform variable
        enemyPosition = new Vector2(platform.left, platform.top + Constants.ENEMY_CENTER.y);

        direction = Direction.RIGHT;
        startTime = TimeUtils.nanoTime();
    }

    public void update(float delta) {

        switch (direction){
            case LEFT:
                enemyPosition.x -= Constants.ENEMY_MOVEMENT_SPEED * delta;
                break;
            case RIGHT:
                enemyPosition.x += Constants.ENEMY_MOVEMENT_SPEED * delta;
        }

        if (enemyPosition.x > platform.right) {
            enemyPosition.x = platform.right;
            direction = Direction.LEFT;
        } else if (enemyPosition.x < platform.left) {
            enemyPosition.x = platform.left;
            direction = Direction.RIGHT;
        }

        final float elapsedTime = Util.secondsSince(startTime);
        final float bobMultiplier = 1 + MathUtils.sin(MathUtils.PI2 *
                elapsedTime/Constants.ENEMY_BOB_PERIOD);

        enemyPosition.y = platform.top + Constants.ENEMY_CENTER.y +
                (bobMultiplier * Constants.ENEMY_BOB_AMPLITUDE);

    }

    public void render(SpriteBatch batch) {
        Util.drawTextureRegion(batch, Assets.instance.enemyAssets.enemyAtlasRegion,
                enemyPosition, Constants.ENEMY_CENTER
        );
    }
}
