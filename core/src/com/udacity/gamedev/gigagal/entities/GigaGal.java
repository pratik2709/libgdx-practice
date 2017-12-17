package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;

import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.Z;
import static com.udacity.gamedev.gigagal.util.Constants.*;

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();

    //add a position
    Vector2 gigagalPosition;

    //facing member variable
    Facing facingDirection;

    //velocity
    Vector2 velocity;

    JumpState jumpState;

    long jumpStartTime;

    public GigaGal(){
        //initialize gigagal position
        //why 20 ?
        gigagalPosition = new Vector2(20, GIGAGAL_EYE_HEIGHT);
        facingDirection = Facing.RIGHT;
        //??
        velocity = new Vector2();
        jumpState =JumpState.FALLING;
    }

    public void render(SpriteBatch batch){
        TextureRegion region;
        if (facingDirection == Facing.RIGHT && jumpState != JumpState.GROUNDED){
            region = Assets.instance.gigaGalAssets.jumpingRight;
        }
        else if(facingDirection == Facing.RIGHT){
            region = Assets.instance.gigaGalAssets.standingRight;
        }
        else if (facingDirection == Facing.LEFT && jumpState != JumpState.GROUNDED){
            region = Assets.instance.gigaGalAssets.jumpingLeft;
        }
        else if (facingDirection == Facing.LEFT){
            region = Assets.instance.gigaGalAssets.standingLeft;
        }
        else{
            region = Assets.instance.gigaGalAssets.standingRight;
        }

        batch.begin();

        batch.draw(
                region.getTexture(),

                gigagalPosition.x - Constants.GIGAGAL_EYE_POSITION.x,
                gigagalPosition.y - Constants.GIGAGAL_EYE_POSITION.y,
                0,0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,1, 0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false
        );
        batch.end();
    }

    public void update(float delta){
        //accelerate
        //no idea ??
        velocity.y -= delta * GRAVITY;

        //apply velocity to position
        gigagalPosition.mulAdd(velocity,delta);

        //if not jumping then means falling
        if((jumpState != JumpState.JUMPING)){
            jumpState = JumpState.FALLING;
        }

        //check if landed on the ground
        //??
        if(gigagalPosition.y - Constants.GIGAGAL_EYE_HEIGHT < 0){
            jumpState = JumpState.GROUNDED;
            gigagalPosition.y = Constants.GIGAGAL_EYE_HEIGHT;
            velocity.y = 0;
        }

        if(Gdx.input.isKeyPressed(Z)){
            switch (jumpState){
                case JUMPING:
                    continueJumping();
                    break;
                case GROUNDED:
                    startJump();
                    break;
                case FALLING:
                    break;
            }
        }
        else {
            endJump();
        }
        if(Gdx.input.isKeyPressed(RIGHT)){

            moveRight(delta);
        }
        else if(Gdx.input.isKeyPressed(LEFT)){
            moveLeft(delta);
        }
    }

    private void startJump() {
        jumpState = JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJumping();
    }

    private void endJump() {
        if(jumpState == JumpState.JUMPING){
            jumpState = JumpState.FALLING;
        }
    }

    private void continueJumping() {
        if((jumpState != JumpState.JUMPING)){
            return;
        }
        else{
            // jump duration
            if(MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartTime) < GIGAGAL_JUMP_DURATION){
                velocity.y = GIGAGAL_JUMP_SPEED;
            }
            else{
                endJump();
            }
        }

    }

    private void moveRight(float delta) {
        facingDirection = Facing.RIGHT;
        gigagalPosition.x += delta * GIGAGAL_MOVE_SPEED;
    }

    private void moveLeft(float delta) {
        facingDirection = Facing.LEFT;
        gigagalPosition.x -= delta * GIGAGAL_MOVE_SPEED;
    }

    enum JumpState {
        JUMPING, FALLING, GROUNDED
    }

    enum Facing{
        RIGHT, LEFT
    }

}
