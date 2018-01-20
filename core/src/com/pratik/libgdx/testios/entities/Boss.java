package com.pratik.libgdx.testios.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Util;


public class Boss {
    Vector2 position;
    long startTime;

    public Boss(Vector2 position) {
        this.position = position;
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
}
