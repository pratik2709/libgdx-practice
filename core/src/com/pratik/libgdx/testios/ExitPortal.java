package com.pratik.libgdx.testios;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.Util;

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
