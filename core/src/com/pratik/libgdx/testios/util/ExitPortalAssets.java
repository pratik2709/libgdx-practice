package com.pratik.libgdx.testios.util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class ExitPortalAssets {

    public final Animation<TextureRegion> exitPortalAnimation;

    public ExitPortalAssets(TextureAtlas atlas) {

        final TextureAtlas.AtlasRegion exitPortal1 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_1);
        final TextureAtlas.AtlasRegion exitPortal2 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_2);
        final TextureAtlas.AtlasRegion exitPortal3 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_3);
        final TextureAtlas.AtlasRegion exitPortal4 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_4);
        final TextureAtlas.AtlasRegion exitPortal5 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_5);
        final TextureAtlas.AtlasRegion exitPortal6 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_6);

        Array<TextureAtlas.AtlasRegion> exitPortalFrames = new Array<TextureAtlas.AtlasRegion>();
        exitPortalFrames.add(exitPortal1);
        exitPortalFrames.add(exitPortal2);
        exitPortalFrames.add(exitPortal3);
        exitPortalFrames.add(exitPortal4);
        exitPortalFrames.add(exitPortal5);
        exitPortalFrames.add(exitPortal6);

        exitPortalAnimation = new Animation<TextureRegion>(Constants.EXIT_PORTAL_FRAME_DURATION,
                exitPortalFrames);
    }
}
