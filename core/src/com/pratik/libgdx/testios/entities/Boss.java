package com.pratik.libgdx.testios.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.pratik.libgdx.testios.Level;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Enums;
import com.pratik.libgdx.testios.util.Util;


public class Boss {
    Vector2 position;
    long startTime;
    Level level;
    float timeAux;

    public Boss(Vector2 position, Level level) {
        this.position = position;
        this.level = level;
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch){
        //scaling sprite
        //also need to scale the center for it to work like overlap2d
        drawTextureRegion(batch,
                Assets.instance.
                        bossAssets.bossAnimation.getKeyFrame(Util.secondsSince(startTime))
                        ,
                position.x - Constants.BOSS_CENTER.x*5,
                position.y - Constants.BOSS_CENTER.y*5);
    }

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y) {
        batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                5,
                5,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    public void update(float delta) {
        //fire the bullet from both cannons
        Vector2 bulletPositionCannonOne;
        Vector2 bulletPositionCannonTwo;

//        System.out.println(position.x + "," + position.y);
        bulletPositionCannonOne = new Vector2(position.x - Constants.BOSS_CENTER.x*5 +
                Constants.BOSS_CANNON_1.x*5,
                position.y - Constants.BOSS_CENTER.y*5 + Constants.BOSS_CANNON_1.y*5);

        bulletPositionCannonTwo = new Vector2(position.x - Constants.BOSS_CENTER.x*5 +
                Constants.BOSS_CANNON_2.x*5,
                position.y - Constants.BOSS_CENTER.y*5 + Constants.BOSS_CANNON_2.y*5);


        if(timeAux >= 0.3f){
            level.spawnBullet(bulletPositionCannonOne, Enums.Direction.LEFT);
            level.spawnBullet(bulletPositionCannonTwo, Enums.Direction.LEFT);
            timeAux = 0;
        }
        else{
            timeAux += delta;
        }

    }
}
