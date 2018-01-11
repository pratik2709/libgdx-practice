package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Util;

public class Explosion {
    Vector2 position;
    long startTime;

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
}
