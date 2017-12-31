package com.udacity.gamedev.gigagal.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PowerupAssets {

    public final TextureAtlas.AtlasRegion powerupRegion;

    public PowerupAssets(TextureAtlas atlas) {
        powerupRegion = atlas.findRegion(Constants.POWERUP_SPRITE);
    }
}
