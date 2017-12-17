package com.udacity.gamedev.gigagal.util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


import static com.udacity.gamedev.gigagal.util.Constants.*;
import static com.udacity.gamedev.gigagal.util.Constants.WALK_1_RIGHT;
import static com.udacity.gamedev.gigagal.util.Constants.WALK_3_RIGHT;

import com.badlogic.gdx.graphics.g2d.Animation;


public class GigaGalAssets {

    public final TextureAtlas.AtlasRegion standingRight;
    public final TextureAtlas.AtlasRegion standingLeft;
    public final TextureAtlas.AtlasRegion jumpingLeft;
    public final TextureAtlas.AtlasRegion jumpingRight;

    public final TextureAtlas.AtlasRegion walkingLeft;
    public final TextureAtlas.AtlasRegion walkingRight;

    public final Animation<TextureRegion> walkingLeftAnimation;
    public final Animation<TextureRegion> walkingRightAnimation;

    public GigaGalAssets(TextureAtlas atlas) {
        standingRight = atlas.findRegion(STANDING_RIGHT);
        standingLeft = atlas.findRegion(STANDING_LEFT);
        jumpingLeft = atlas.findRegion(JUMPING_LEFT);
        jumpingRight = atlas.findRegion(JUMPING_RIGHT);
        walkingLeft = atlas.findRegion(WALK_1_LEFT);
        walkingRight = atlas.findRegion(WALK_1_RIGHT);

        //array of atlas regions
        Array<TextureAtlas.AtlasRegion> walkingLeftFrames = new Array<TextureAtlas.AtlasRegion>();

        walkingLeftFrames.add(atlas.findRegion(WALK_2_LEFT));
        walkingLeftFrames.add(atlas.findRegion(WALK_1_LEFT));
        walkingLeftFrames.add(atlas.findRegion(WALK_2_LEFT));
        walkingLeftFrames.add(atlas.findRegion(WALK_3_LEFT));

        walkingLeftAnimation = new Animation(Constants.WALK_LOOP_DURATION, walkingLeftFrames, PlayMode.LOOP);

        Array<TextureAtlas.AtlasRegion> walkingRightFrames = new Array<TextureAtlas.AtlasRegion>();

        walkingRightFrames.add(atlas.findRegion(WALK_2_RIGHT));
        walkingRightFrames.add(atlas.findRegion(WALK_1_RIGHT));
        walkingRightFrames.add(atlas.findRegion(WALK_2_RIGHT));
        walkingRightFrames.add(atlas.findRegion(WALK_3_RIGHT));

        walkingRightAnimation = new Animation(Constants.WALK_LOOP_DURATION, walkingRightFrames, PlayMode.LOOP);
    }
}
