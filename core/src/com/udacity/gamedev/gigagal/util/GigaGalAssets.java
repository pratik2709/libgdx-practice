package com.udacity.gamedev.gigagal.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import static com.udacity.gamedev.gigagal.util.Constants.STANDING_LEFT;
import static com.udacity.gamedev.gigagal.util.Constants.STANDING_RIGHT;

public class GigaGalAssets {

    public final TextureAtlas.AtlasRegion standingRight;
    public final TextureAtlas.AtlasRegion standingLeft;

    public GigaGalAssets(TextureAtlas atlas) {
        standingRight = atlas.findRegion(STANDING_RIGHT);
        standingLeft = atlas.findRegion(STANDING_LEFT);
    }
}
