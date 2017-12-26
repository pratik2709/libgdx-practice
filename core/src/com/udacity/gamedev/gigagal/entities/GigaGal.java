package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Util;

import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.Z;
import static com.udacity.gamedev.gigagal.util.Constants.*;

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();

    Vector2 spawnLocation;

    //add a position
    public Vector2 gigagalPosition;

    //gigagal's position in last frame
    Vector2 lastFramePosition;

    //facing member variable
    Facing facingDirection;

    //velocity
    Vector2 velocity;

    JumpState jumpState;

    long jumpStartTime;

    WalkState walkState;

    long walkStartTime;

    public GigaGal(Vector2 position) {
        this.spawnLocation = position;
        gigagalPosition = new Vector2();
        lastFramePosition = new Vector2();
        velocity = new Vector2();

        init();
    }

    public void init(){
        gigagalPosition.set(spawnLocation);
        lastFramePosition.set(gigagalPosition);
        velocity.y = 0;
        jumpState = JumpState.FALLING;
        facingDirection = Facing.RIGHT;
        walkState = WalkState.STANDING;
    }

    public void render(SpriteBatch batch) {

        TextureRegion region;

        if (facingDirection == Facing.RIGHT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.gigaGalAssets.jumpingRight;
        } else if (facingDirection == Facing.RIGHT && walkState == WalkState.STANDING) {
            region = Assets.instance.gigaGalAssets.standingRight;
        } else if (facingDirection == Facing.RIGHT && walkState == WalkState.WALKING) {
            //how long we
            float walkElapsed = secondsSince();

            region = Assets.instance.gigaGalAssets.walkingRightAnimation.getKeyFrame(walkElapsed);
        } else if (facingDirection == Facing.LEFT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.gigaGalAssets.jumpingLeft;
        } else if (facingDirection == Facing.LEFT && walkState == WalkState.STANDING) {
            region = Assets.instance.gigaGalAssets.standingLeft;
        } else if (facingDirection == Facing.LEFT && walkState == WalkState.WALKING) {
            float walkElapsed = secondsSince();

            region = Assets.instance.gigaGalAssets.walkingLeftAnimation.getKeyFrame(walkElapsed);
        } else {
            region = null;
        }

        batch.begin();
        Util.drawTextureRegion(batch, region,  gigagalPosition, Constants.GIGAGAL_EYE_POSITION);
        batch.end();
    }

    private float secondsSince() {
        return MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
    }

    public void update(float delta, Array<Platform> platforms) {

//        System.out.println(gigagalPosition.y);
        //last frame position
        lastFramePosition.set(gigagalPosition);

        //accelerate
        //no idea ??
        velocity.y -= delta * GRAVITY;

        //apply velocity to position
        gigagalPosition.mulAdd(velocity, delta);

        if(gigagalPosition.y - Constants.GIGAGAL_EYE_HEIGHT < Constants.KILL_PLANE){
            init();
        }

        //if not jumping then means falling
        if ((jumpState != JumpState.JUMPING)) {
            jumpState = JumpState.FALLING;

            //check if landed on the ground
            //??
            //remove the ground
//            if (gigagalPosition.y - Constants.GIGAGAL_EYE_HEIGHT < 0) {
//                jumpState = JumpState.GROUNDED;
//                gigagalPosition.y = Constants.GIGAGAL_EYE_HEIGHT;
//                velocity.y = 0;
//            }

//            System.out.println(platforms.size);
            //check if landed on the platform
            for (Platform platform : platforms) {
                if (landedOnPlatform(platform)) {
                    jumpState = JumpState.GROUNDED;
                    velocity.y = 0;
                    gigagalPosition.y = platform.top + Constants.GIGAGAL_EYE_HEIGHT;
                }
            }
        }


        if (Gdx.input.isKeyPressed(Z)) {
            switch (jumpState) {
                case JUMPING:
                    continueJumping();
                    break;
                case GROUNDED:
                    startJump();
                    break;
                case FALLING:
                    break;
            }
        } else {
            endJump();
        }
        if (Gdx.input.isKeyPressed(RIGHT)) {

            moveRight(delta);
        } else if (Gdx.input.isKeyPressed(LEFT)) {
            moveLeft(delta);
        } else {
            // no left and right so stand right there
            walkState = WalkState.STANDING;
        }
    }

    private boolean landedOnPlatform(Platform platform) {
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;

        //??
        if (lastFramePosition.y - Constants.GIGAGAL_EYE_HEIGHT >= platform.top &&
                gigagalPosition.y - Constants.GIGAGAL_EYE_HEIGHT < platform.top) {


            //position of left and right toes
            float leftFoot = gigagalPosition.x - Constants.GIGAGAL_STANCE_WIDTH / 2;
            float rightFoot = gigagalPosition.x + Constants.GIGAGAL_STANCE_WIDTH / 2;

            //toes on the platform
            //??
            leftFootIn = (platform.left < leftFoot && platform.right > leftFoot);
            rightFootIn = (platform.left < rightFoot && platform.right > rightFoot);

            //??
            straddle = (platform.left > leftFoot && platform.right < rightFoot);

        }
        return leftFootIn || rightFootIn || straddle;
    }

    private void startJump() {
        jumpState = JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJumping();
    }

    private void endJump() {
        if (jumpState == JumpState.JUMPING) {
            jumpState = JumpState.FALLING;
        }
    }

    private void continueJumping() {
        if ((jumpState != JumpState.JUMPING)) {
            return;
        } else {
            // jump duration
            if (MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartTime) < GIGAGAL_JUMP_DURATION) {
                velocity.y = GIGAGAL_JUMP_SPEED;
            } else {
                endJump();
            }
        }

    }

    private void moveRight(float delta) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facingDirection = Facing.RIGHT;
        gigagalPosition.x += delta * GIGAGAL_MOVE_SPEED;

    }

    private void moveLeft(float delta) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facingDirection = Facing.LEFT;
        gigagalPosition.x -= delta * GIGAGAL_MOVE_SPEED;
    }

    enum JumpState {
        JUMPING, FALLING, GROUNDED
    }

    enum Facing {
        RIGHT, LEFT
    }

    enum WalkState {
        STANDING,
        WALKING
    }

}
