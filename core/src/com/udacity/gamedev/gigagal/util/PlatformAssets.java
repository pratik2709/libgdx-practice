package com.udacity.gamedev.gigagal.util;


import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import static com.udacity.gamedev.gigagal.util.Constants.EDGE;
import static com.udacity.gamedev.gigagal.util.Constants.PLATFORM_SPRITE;

public class PlatformAssets {

    public final NinePatch platformNinePatch;

    public PlatformAssets(TextureAtlas atlas){
        //find atlas region
        TextureAtlas.AtlasRegion atlasRegion = atlas.findRegion(PLATFORM_SPRITE);
        platformNinePatch = new NinePatch(atlasRegion, EDGE,EDGE,EDGE,EDGE);
    }
}
