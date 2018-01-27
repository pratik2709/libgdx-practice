package com.pratik.libgdx.testios.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.pratik.libgdx.testios.Level;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Enums;
import com.pratik.libgdx.testios.util.Util;


public class Boss {
    Vector2 position;
    final Vector2 actPos;
    long startTime;
    Level level;
    float timeAux;
    float timeAux1;
    Vector2 velocity;
    Enums.JumpState jumpState;
    long startJumpTime;

    public Boss(Vector2 position, Level level) {
        this.position = position;
        actPos = new Vector2();
        actPos.set(position);
        this.level = level;
        startTime = TimeUtils.nanoTime();
        velocity = new Vector2();
        velocity.setZero();
        jumpState = Enums.JumpState.FALLING;
    }

    public void render(SpriteBatch batch) {
        //scaling sprite
        //also need to scale the center for it to work like overlap2d
        drawTextureRegion(batch,
                Assets.instance.
                        bossAssets.bossAnimation.getKeyFrame(Util.secondsSince(startTime))
                ,
                position.x - Constants.BOSS_CENTER.x * 5,
                position.y - Constants.BOSS_CENTER.y * 5);
    }

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y) {
        batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                5,
                5,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    public void update(float delta, Array<Platform> platforms) {
        handleCannonFire(delta);
        velocity.y -= delta * Constants.GRAVITY;
        position.mulAdd(velocity, delta);

        for(Platform platform: platforms){
            if(landedOnPlatform(platform)){
                jumpState = Enums.JumpState.GROUNDED;
                velocity.x = 0;
                velocity.y = 0;
                position.set(actPos);
                break;
            }
        }
        //randomly make the boss jump
        System.out.println(timeAux1);
        if (timeAux1 >= 5f) {
            handleJumpCases();
            timeAux1 = 0;
        } else {
            endJump();
            timeAux1 += delta;
        }
    }

    private boolean landedOnPlatform(Platform platform) {
        if(position.y <= actPos.y){
            return true;
        }
        else{
            return false;
        }
    }

    private void handleJumpCases() {
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
    }

    private void startJump() {
        jumpState = Enums.JumpState.JUMPING;
        startJumpTime = TimeUtils.nanoTime();
        continueJumping();
    }

    private void continueJumping() {
        if (jumpState != Enums.JumpState.JUMPING)
            return;
        else {
            if (MathUtils.nanoToSec * (TimeUtils.nanoTime() - startJumpTime) <
                    Constants.BOSS_JUMP_DURATION) {
                velocity.y = Constants.BOSS_JUMP_SPEED;
            }
            else{
                endJump();
            }
        }
    }

    private void endJump() {
        if(jumpState == Enums.JumpState.JUMPING){
            jumpState = Enums.JumpState.FALLING;
        }
    }

    private void handleCannonFire(float delta) {
        //fire the bullet from both cannons
        Vector2 bulletPositionCannonOne;
        Vector2 bulletPositionCannonTwo;

//        System.out.println(position.x + "," + position.y);
        bulletPositionCannonOne = new Vector2(position.x - Constants.BOSS_CENTER.x * 5 +
                Constants.BOSS_CANNON_1.x * 5,
                position.y - Constants.BOSS_CENTER.y * 5 + Constants.BOSS_CANNON_1.y * 5);

        bulletPositionCannonTwo = new Vector2(position.x - Constants.BOSS_CENTER.x * 5 +
                Constants.BOSS_CANNON_2.x * 5,
                position.y - Constants.BOSS_CENTER.y * 5 + Constants.BOSS_CANNON_2.y * 5);


        if (timeAux >= 0.3f) {
            level.spawnBullet(bulletPositionCannonOne, Enums.Direction.LEFT);
            level.spawnBullet(bulletPositionCannonTwo, Enums.Direction.LEFT);
            timeAux = 0;
        } else {
            timeAux += delta;
        }
    }
}
