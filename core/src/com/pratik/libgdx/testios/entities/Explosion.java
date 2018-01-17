package com.pratik.libgdx.testios.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Util;

public class Explosion {
    public Vector2 position;
    long startTime;
    public float offset = 0;

    public Explosion(Vector2 position){
        this.position = position;
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch){
        Util.drawTextureRegion(batch,
                Assets.instance.explosionAssets.explosionAnimation.
                        getKeyFrame(Util.secondsSince(startTime)),
                position, Constants.EXPLOSION_CENTER);
    }

    public boolean isFinished(){
        final float elapsedTime = Util.secondsSince(startTime);
        return Assets.instance.explosionAssets.explosionAnimation.isAnimationFinished(elapsedTime);
    }

    public boolean yetToStart(){
        return Util.secondsSince(startTime) - offset < 0;
    }
}
