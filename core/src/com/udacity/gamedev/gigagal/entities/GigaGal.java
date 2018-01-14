package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.Level;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Enums;
import com.udacity.gamedev.gigagal.util.Util;

import static com.badlogic.gdx.Input.Keys.*;
import static com.udacity.gamedev.gigagal.util.Constants.*;

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();

    Vector2 spawnLocation;

    //add a position
    public Vector2 gigagalPosition;

    //gigagal's position in last frame
    Vector2 lastFramePosition;

    //facing member variable
    Enums.Direction facingDirection;

    //velocity
    Vector2 velocity;

    JumpState jumpState;

    long jumpStartTime;

    WalkState walkState;

    long walkStartTime;

    private int ammoCount;

    private int lives;

    Level level;

    public GigaGal(Vector2 position, Level level) {
        this.level = level;
        this.spawnLocation = position;
        gigagalPosition = new Vector2();
        lastFramePosition = new Vector2();
        velocity = new Vector2();
        lives = Constants.GIGAGAL_LIVES;
        init();
    }

    public void init() {
        gigagalPosition.set(spawnLocation);
        lastFramePosition.set(gigagalPosition);
        velocity.setZero();
        jumpState = JumpState.FALLING;
        facingDirection = Enums.Direction.RIGHT;
        walkState = WalkState.STANDING;
        ammoCount = Constants.INITIAL_AMMO;
    }

    public void render(SpriteBatch batch) {

        TextureRegion region;

        if (facingDirection == Enums.Direction.RIGHT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.gigaGalAssets.jumpingRight;
        } else if (facingDirection == Enums.Direction.RIGHT && walkState == WalkState.STANDING) {
            region = Assets.instance.gigaGalAssets.standingRight;
        } else if (facingDirection == Enums.Direction.RIGHT && walkState == WalkState.WALKING) {
            //how long we
            float walkElapsed = secondsSince();

            region = Assets.instance.gigaGalAssets.walkingRightAnimation.getKeyFrame(walkElapsed);
        } else if (facingDirection == Enums.Direction.LEFT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.gigaGalAssets.jumpingLeft;
        } else if (facingDirection == Enums.Direction.LEFT && walkState == WalkState.STANDING) {
            region = Assets.instance.gigaGalAssets.standingLeft;
        } else if (facingDirection == Enums.Direction.LEFT && walkState == WalkState.WALKING) {
            float walkElapsed = secondsSince();

            region = Assets.instance.gigaGalAssets.walkingLeftAnimation.getKeyFrame(walkElapsed);
        } else {
            region = null;
        }

        batch.begin();

        Util.drawTextureRegion(batch, region, gigagalPosition, Constants.GIGAGAL_EYE_POSITION);
        batch.end();
    }

    private float secondsSince() {
        return MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
    }

    public void update(float delta, Array<Platform> platforms) {

        //last frame position
        lastFramePosition.set(gigagalPosition);

        //accelerate
        //no idea ??
        velocity.y -= delta * GRAVITY;

        //apply velocity to position
        gigagalPosition.mulAdd(velocity, delta);

        if (gigagalPosition.y - Constants.GIGAGAL_EYE_HEIGHT < Constants.KILL_PLANE) {
            lives--;
            init();
        }

        //if not jumping then means falling
        if ((jumpState != JumpState.JUMPING)) {

            //??
            if (jumpState != JumpState.RECOILING) {
                jumpState = JumpState.FALLING;
            }


            //check if landed on the platform
            for (Platform platform : platforms) {
                if (landedOnPlatform(platform)) {
                    jumpState = JumpState.GROUNDED;
                    velocity.y = 0;
                    velocity.x = 0;
                    gigagalPosition.y = platform.top + Constants.GIGAGAL_EYE_HEIGHT;
                }
            }
        }

        //collision code
        Rectangle gigagalRectangle = new Rectangle(gigagalPosition.x - Constants.GIGAGAL_STANCE_WIDTH / 2,
                gigagalPosition.y - Constants.GIGAGAL_EYE_HEIGHT,
                Constants.GIGAGAL_STANCE_WIDTH,
                Constants.GIGAGAL_HEIGHT);

        for (Enemy enemy : level.getEnemies()) {
            Rectangle enemyRectangle = new Rectangle(enemy.enemyPosition.x - Constants.ENEMY_COLLISION_RADIUS,
                    enemy.enemyPosition.y - Constants.ENEMY_COLLISION_RADIUS,
                    2 * Constants.ENEMY_COLLISION_RADIUS,
                    2 * Constants.ENEMY_COLLISION_RADIUS
            );
            if (gigagalRectangle.overlaps(enemyRectangle)) {
                if (gigagalPosition.x < enemy.enemyPosition.x) {
                    recoilFromEnemy(Enums.Direction.LEFT);
                } else {
                    recoilFromEnemy(Enums.Direction.RIGHT);
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

        if (Gdx.input.isKeyPressed(RIGHT) && jumpState != JumpState.RECOILING) {

            moveRight(delta);
        } else if (Gdx.input.isKeyPressed(LEFT) && jumpState != JumpState.RECOILING) {
            moveLeft(delta);
        } else {
            // no left and right so stand right there
            walkState = WalkState.STANDING;
        }

        handlePowerups(gigagalRectangle);

            if (Gdx.input.isKeyJustPressed(X) && ammoCount > 0) {
                Vector2 bulletPosition;
                ammoCount -= 1;
                if (facingDirection == Enums.Direction.RIGHT) {
                    //position tracks the eye and to get the canon position add the
                    // canon offset
                    bulletPosition = new Vector2(gigagalPosition.x + Constants.GIGAGAL_EYE_BARREL_OFFSET.x,
                            gigagalPosition.y + Constants.GIGAGAL_EYE_BARREL_OFFSET.y);
                    level.spawnBullet(bulletPosition, facingDirection);
                } else {
                    bulletPosition = new Vector2(gigagalPosition.x - Constants.GIGAGAL_EYE_BARREL_OFFSET.x,
                            gigagalPosition.y + Constants.GIGAGAL_EYE_BARREL_OFFSET.y);
                    level.spawnBullet(bulletPosition, facingDirection);

                }

            }

    }

    private void handlePowerups(Rectangle gigagalRectangle) {
        //detect collision with powerup
        //remove picked up powerups
        DelayedRemovalArray<Powerup> powerups = level.getPowerups();
        powerups.begin();
        for (Powerup powerup : powerups) {
            Rectangle powerupRectangle = new Rectangle(
                    powerup.position.x - Constants.POWERUP_CENTER.x,
                    powerup.position.y - Constants.POWERUP_CENTER.y,
                    Assets.instance.powerupAssets.powerupRegion.getRegionWidth(),
                    Assets.instance.powerupAssets.powerupRegion.getRegionHeight()
            );

            if (gigagalRectangle.overlaps(powerupRectangle)) {
                ammoCount += Constants.POWERUP_SCORE;
                powerups.removeValue(powerup, false);
            }
        }
        powerups.end();
    }

    private void recoilFromEnemy(Enums.Direction direction) {
        jumpState = JumpState.RECOILING;
        velocity.y = Constants.GIGAGAL_KICKBACK_VELOCITY.y;

        if (direction == Enums.Direction.LEFT) {
            velocity.x = -Constants.GIGAGAL_KICKBACK_VELOCITY.x;
        } else if (direction == Enums.Direction.RIGHT) {
            velocity.x = Constants.GIGAGAL_KICKBACK_VELOCITY.x;
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
        facingDirection = facingDirection.RIGHT;
        gigagalPosition.x += delta * GIGAGAL_MOVE_SPEED;

    }

    private void moveLeft(float delta) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facingDirection = facingDirection.LEFT;
        gigagalPosition.x -= delta * GIGAGAL_MOVE_SPEED;
    }

    enum JumpState {
        JUMPING, FALLING, GROUNDED, RECOILING
    }

    enum Facing {
        RIGHT, LEFT
    }

    enum WalkState {
        STANDING,
        WALKING
    }

    public int getAmmoCount() {
        return ammoCount;
    }

    public int getLives(){
        return lives;
    }
}
