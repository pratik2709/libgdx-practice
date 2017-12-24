package com.udacity.gamedev.gigagal.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    //background color
    public static final Color BACKGROUND_COLOR = Color.SKY;


    public static final float WORLD_SIZE = 128;

    public static final String TEXTURE_ATLAS = "images/gigagal.pack.atlas";

    //constant for standing right
    public static final String STANDING_RIGHT = "standing-right";

    //left sprite
    public static final String STANDING_LEFT = "standing-left";

    //jumping-right and jumping-left sprite
    public static final String JUMPING_LEFT = "jumping-left";
    public static final String JUMPING_RIGHT = "jumping-right";

    //walk sprites
    public static final String WALK_2_LEFT = "walk-2-left";
    public static final String WALK_2_RIGHT = "walk-2-right";

    //4 other walking sprites
    public static final String WALK_1_LEFT = "walk-1-left";
    public static final String WALK_1_RIGHT = "walk-1-right";
    public static final String WALK_3_LEFT = "walk-3-left";
    public static final String WALK_3_RIGHT = "walk-3-right";

    //Gigagal's eye postion within her sprites
    public static  final Vector2 GIGAGAL_EYE_POSITION = new Vector2(16,24);

    //float constant for gigagal's eye above her feet
    public static final float GIGAGAL_EYE_HEIGHT = 16.0f;

    //walk loop duration
    public static final float WALK_LOOP_DURATION = 0.25f;

    //platform sprite
    public static final String PLATFORM_SPRITE = "platform";

    //stretchable edges in platform 9 patch
    public static final int EDGE = 8;

    public static final int GIGAGAL_MOVE_SPEED = 40;

    //gigagal jump speed
    public static final int GIGAGAL_JUMP_SPEED = 250;

    //gigagal jump duration
    public static final double GIGAGAL_JUMP_DURATION = 0.15;

    //gigagal gravity
    public static final int GRAVITY = 1000;

    //gigagal's stance width ??
    public static final float GIGAGAL_STANCE_WIDTH =  21.0f;
}
