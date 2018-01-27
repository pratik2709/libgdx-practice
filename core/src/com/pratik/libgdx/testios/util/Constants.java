package com.pratik.libgdx.testios.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    //background color
    public static final Color BACKGROUND_COLOR = Color.SKY;


    public static final float WORLD_SIZE = 128;

    //height of the kill plane
    public static final int KILL_PLANE = -100;

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

    public static final String ENEMY = "enemy";
    public static final Vector2 ENEMY_CENTER = new Vector2(14,22);
    public static final int ENEMY_MOVEMENT_SPEED = 10;
    public static final int ENEMY_BOB_AMPLITUDE = 2;
    public static final int ENEMY_BOB_PERIOD = 3;

    public static final int ENEMY_HEALTH = 5;
    public static final int ENEMY_HIT_DETECTION_RADIUS = 17;

    //Gigagal's eye postion within her sprites
    public static  final Vector2 GIGAGAL_EYE_POSITION = new Vector2(16,24);

    //float constant for gigagal's eye above her feet
    public static final float GIGAGAL_EYE_HEIGHT = 16.0f;
    public static final float GIGAGAL_HEIGHT = 23.0f;
    public static final Vector2 GIGAGAL_KICKBACK_VELOCITY = new Vector2(200,200);

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

    public static final Vector2 GIGAGAL_EYE_BARREL_OFFSET = new Vector2(12,-7);

    public static final float CAMERA_MOVE_SPEED =  100.0f;

    public static final int ENEMY_COLLISION_RADIUS = 15;

    // TODO: Note the constants we've added for the bullets
    public static final String BULLET_SPRITE = "bullet";
    public static final Vector2 BULLET_CENTER = new Vector2(3, 2);

    // TODO: Note the constants we've added for the explosions
    public static final String EXPLOSION_LARGE = "explosion-large";
    public static final String EXPLOSION_MEDIUM = "explosion-medium";
    public static final String EXPLOSION_SMALL = "explosion-small";
    public static final Vector2 EXPLOSION_CENTER = new Vector2(8, 8);
    public static final float EXPLOSION_DURATION = 0.5f;


    public static final float BULLET_MOVE_SPEED = 150;

    // TODO: Note the constants we've added for the powerups
    public static final String POWERUP_SPRITE = "powerup";
    public static final Vector2 POWERUP_CENTER = new Vector2(7, 5);
    public static final int POWERUP_COLLISION_RADIUS =1;
    //constant for initial ammo
    public static final int INITIAL_AMMO = 5;

    // constant for ammo powerup
    public static final int AMMO_POWERUP = 5;

    public static final String LEVEL_DIR = "levels";
    public static final String LEVEL_FILE_EXTENSION = "json";
    public static final String LEVEL_COMPOSITE = "composite";
    public static final String LEVEL_9PATCHES = "sImage9patchs";
    public static final String LEVEL_IMAGES = "sImages";
    public static final String LEVEL_ERROR_MESSAGE = "There was a problem loading the level.";
    public static final String LEVEL_IMAGENAME_KEY = "imageName";
    public static final String LEVEL_X_KEY = "x";
    public static final String LEVEL_Y_KEY = "y";
    public static final String LEVEL_WIDTH_KEY = "width";
    public static final String LEVEL_HEIGHT_KEY = "height";
    public static final String LEVEL_IDENTIFIER_KEY = "itemIdentifier";
    public static final String LEVEL_ENEMY_TAG = "Enemy";

    //exit portal
    public static final String EXIT_PORTAL_SPRITE_1 = "exit-portal-1";
    public static final String EXIT_PORTAL_SPRITE_2 = "exit-portal-2";
    public static final String EXIT_PORTAL_SPRITE_3 = "exit-portal-3";
    public static final String EXIT_PORTAL_SPRITE_4 = "exit-portal-4";
    public static final String EXIT_PORTAL_SPRITE_5 = "exit-portal-5";
    public static final String EXIT_PORTAL_SPRITE_6 = "exit-portal-6";
    public static final Vector2 EXIT_PORTAL_CENTER = new Vector2(31, 31);

    public static final float EXIT_PORTAL_FRAME_DURATION = 0.10f;
    public static final float EXIT_PORTAL_RADIUS = 28;
    public static final Vector2 EXIT_PORTAL_DEFAULT_LOCATION = new Vector2(252,400);

    //HUD
    public static final float HUD_VIEWPORT_SIZE = 480;
    public static final float HUD_MARGIN = 30;
    public static final String HUD_AMMO_LABEL = "Ammo: ";
    public static final String HUD_SCORE_LABEL = "Score: ";

    public static final int GIGAGAL_LIVES = 5;
    public static final int ENEMY_KILL_SCORE = 100;
    public static final int ENEMY_HIT_SCORE = 25;
    public static final int POWERUP_SCORE = 50;

    // Onscreen Controls
    public static final float ONSCREEN_CONTROLS_VIEWPORT_SIZE = 200;
    public static final String MOVE_LEFT_BUTTON = "button-move-left";
    public static final String MOVE_RIGHT_BUTTON = "button-move-right";
    public static final String SHOOT_BUTTON = "button-shoot";
    public static final String JUMP_BUTTON = "button-jump";
    public static final Vector2 BUTTON_CENTER = new Vector2(15, 15);
    public static final float BUTTON_RADIUS = 32;

    //Victory Game over scenes
    public static final float LEVEL_END_DURATION = 5;
    public static final String FONT_FILE = "font/header.fnt";

    public static final int EXPLOSION_COUNT = 100;

    public static final String BOSS_FRAME_1 = "Boss1";
    public static final String BOSS_FRAME_2 = "Boss2";
    public static final String BOSS_FRAME_3 = "Boss3";
    public static final float BOSS_COLOR_DURATION = 3;
    public static final String BOSS_SPRITE = "Boss1";
    public static final Vector2 BOSS_CENTER = new Vector2(16,16);
//    public static final Vector2 BOSS_CANNON_1 = new Vector2(9,4);
//    public static final Vector2 BOSS_CANNON_2 = new Vector2(9,0);
    public static final Vector2 BOSS_CANNON_1 = new Vector2(9,16);
    public static final Vector2 BOSS_CANNON_2 = new Vector2(9,27);
    public static final int BOSS_JUMP_SPEED = 250;
    public static final double BOSS_JUMP_DURATION = 0.15;
}
