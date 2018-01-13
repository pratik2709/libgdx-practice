package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Util;

public class ExitPortal {
    final public Vector2 position;
    long startTime;

    public ExitPortal(Vector2 position){
        this.position = position;
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch){
        Util.drawTextureRegion(batch,
                Assets.instance.
                        exitPortalAssets.exitPortalAnimation.
                        getKeyFrame(Util.secondsSince(startTime), true),
                position,
                Constants.EXIT_PORTAL_CENTER
                );
    }
}
