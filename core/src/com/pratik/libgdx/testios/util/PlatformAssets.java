package com.pratik.libgdx.testios.util;


import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PlatformAssets {

    public final NinePatch platformNinePatch;

    public PlatformAssets(TextureAtlas atlas){
        //find atlas region
        TextureAtlas.AtlasRegion atlasRegion = atlas.findRegion(Constants.PLATFORM_SPRITE);
        platformNinePatch = new NinePatch(atlasRegion, Constants.EDGE, Constants.EDGE, Constants.EDGE, Constants.EDGE);
    }
}
