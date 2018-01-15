package com.pratik.libgdx.testios.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class BulletAssets {

    public final TextureAtlas.AtlasRegion bulletRegion;

    public BulletAssets(TextureAtlas atlas) {
        bulletRegion = atlas.findRegion(Constants.BULLET_SPRITE);
    }
}
