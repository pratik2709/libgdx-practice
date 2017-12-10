package com.udacity.gamedev.gigagal.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import static com.udacity.gamedev.gigagal.util.Constants.STANDING_RIGHT;

public class GigaGalAssets {

    public final TextureAtlas.AtlasRegion atlasRegion;

    public GigaGalAssets(TextureAtlas atlas) {
        atlasRegion = atlas.findRegion(STANDING_RIGHT);

    }
}
