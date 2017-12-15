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

    //Gigagal's eye postion within her sprites
    public static  final Vector2 GIGAGAL_EYE_POSITION = new Vector2(16,24);

    //float constant for gigagal's eye above her feet
    public static final float GIGAGAL_EYE_HEIGHT = 16.0f;

    public static final int GIGAGAL_MOVE_SPEED = 40;
}
