package com.pratik.libgdx.testios.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Enums;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.Util;

public class Enemy {

    Platform platform;
    public Vector2 enemyPosition;

    private Enums.Direction direction;
    final long startTime;
    public int healthCounter;


    public Enemy(Platform platform) {
        this.platform = platform;
        // initialize platform variable
        enemyPosition = new Vector2(platform.left, platform.top + Constants.ENEMY_CENTER.y);

        direction = Enums.Direction.RIGHT;
        startTime = TimeUtils.nanoTime();

        healthCounter = Constants.ENEMY_HEALTH;
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
            direction = Enums.Direction.LEFT;
        } else if (enemyPosition.x < platform.left) {
            enemyPosition.x = platform.left;
            direction = Enums.Direction.RIGHT;
        }

        final float elapsedTime = Util.secondsSince(startTime);
        //??
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
