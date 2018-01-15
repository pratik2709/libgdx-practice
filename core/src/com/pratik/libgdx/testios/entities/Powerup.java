package com.pratik.libgdx.testios.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Util;

public class Powerup {
    final public Vector2 position;

    public Powerup(Vector2 position){
        this.position = position;
    }

    public void render(SpriteBatch batch){
        Util.drawTextureRegion(batch,
                Assets.instance.powerupAssets.powerupRegion,
                position,
                Constants.POWERUP_CENTER);
    }
}
