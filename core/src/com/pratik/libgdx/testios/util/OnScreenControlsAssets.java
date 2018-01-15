package com.pratik.libgdx.testios.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class OnScreenControlsAssets {

    public final TextureAtlas.AtlasRegion moveLeftButton;
    public final TextureAtlas.AtlasRegion moveRightButton;
    public final TextureAtlas.AtlasRegion shootButton;
    public final TextureAtlas.AtlasRegion jumpButton;

    public OnScreenControlsAssets(TextureAtlas atlas){
        moveLeftButton = atlas.findRegion(Constants.MOVE_LEFT_BUTTON);
        moveRightButton =atlas.findRegion(Constants.MOVE_RIGHT_BUTTON);
        shootButton = atlas.findRegion(Constants.SHOOT_BUTTON);
        jumpButton = atlas.findRegion(Constants.JUMP_BUTTON);
    }
}
