package com.pratik.libgdx.testios.util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class BossAssets {
    public Animation<TextureRegion> bossAnimation;

    public BossAssets(TextureAtlas atlas) {
        Array<TextureAtlas.AtlasRegion> bossAnimationFrames =
                new Array<TextureAtlas.AtlasRegion>();

        bossAnimationFrames.add(atlas.findRegion(Constants.BOSS_FRAME_1));
        bossAnimationFrames.add(atlas.findRegion(Constants.BOSS_FRAME_2));
        bossAnimationFrames.add(atlas.findRegion(Constants.BOSS_FRAME_3));

        bossAnimation = new Animation<TextureRegion>(
                Constants.BOSS_COLOR_DURATION,
                bossAnimationFrames,
                Animation.PlayMode.LOOP);
    }
}
