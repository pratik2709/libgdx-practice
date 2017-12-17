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
    public static final String WALK_LEFT = "walk-2-left";
    public static final String WALK_RIGHT = "walk-2-right";

    //Gigagal's eye postion within her sprites
    public static  final Vector2 GIGAGAL_EYE_POSITION = new Vector2(16,24);

    //float constant for gigagal's eye above her feet
    public static final float GIGAGAL_EYE_HEIGHT = 16.0f;

    public static final int GIGAGAL_MOVE_SPEED = 40;

    //gigagal jump speed
    public static final int GIGAGAL_JUMP_SPEED = 250;

    //gigagal jump duration
    public static final double GIGAGAL_JUMP_DURATION = 0.15;

    //gigagal gravity
    public static final int GRAVITY = 1000;
}
