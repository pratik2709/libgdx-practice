package com.udacity.gamedev.gigagal.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import static com.udacity.gamedev.gigagal.util.Constants.*;

public class GigaGalAssets {

    public final TextureAtlas.AtlasRegion standingRight;
    public final TextureAtlas.AtlasRegion standingLeft;
    public final TextureAtlas.AtlasRegion jumpingLeft;
    public final TextureAtlas.AtlasRegion jumpingRight;

    public final TextureAtlas.AtlasRegion walkingLeft;
    public final TextureAtlas.AtlasRegion walkingRight;

    public GigaGalAssets(TextureAtlas atlas) {
        standingRight = atlas.findRegion(STANDING_RIGHT);
        standingLeft = atlas.findRegion(STANDING_LEFT);
        jumpingLeft = atlas.findRegion(JUMPING_LEFT);
        jumpingRight = atlas.findRegion(JUMPING_RIGHT);
        walkingLeft = atlas.findRegion(WALK_LEFT);
        walkingRight = atlas.findRegion(WALK_RIGHT);
    }
}
