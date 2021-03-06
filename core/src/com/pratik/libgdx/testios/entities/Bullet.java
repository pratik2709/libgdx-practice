package com.pratik.libgdx.testios.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Enums;
import com.pratik.libgdx.testios.Level;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.Util;

public class Bullet {
    private final Enums.Direction direction;
    private Vector2 position;
    private final Level level;
    private Boolean bulletActive;

    public Bullet(Level level, Vector2 position, Enums.Direction direction){
        this.level = level;
        this.position = position;
        this.direction = direction;
        bulletActive = true;

    }

    public void update(float delta){
        switch (direction){
            case LEFT:
                position.x -= delta * Constants.BULLET_MOVE_SPEED;
                break;
            case RIGHT:
                position.x += delta * Constants.BULLET_MOVE_SPEED;
                break;
        }

        for(Enemy enemy: level.getEnemies()){
            //??
            if(position.dst(enemy.enemyPosition) < Constants.ENEMY_COLLISION_RADIUS){
                level.score += Constants.ENEMY_HIT_SCORE;
                level.spawnExplosion(enemy.enemyPosition);
                bulletActive = false;
                enemy.healthCounter -= 1;
            }
        }

        float worldWidth = level.getViewport().getWorldWidth();
        float cameraHorizontalPosition = level.getViewport().getCamera().position.x;
        //??
        if(position.x > cameraHorizontalPosition + worldWidth/2 ||
                position.x < cameraHorizontalPosition - worldWidth/2){
            bulletActive = false;
        }
    }

    public void render(SpriteBatch batch){
        //render function
        Util.drawTextureRegion(batch, Assets.instance.bulletAssets.bulletRegion, position,
                Constants.BULLET_CENTER);
    }

    public Boolean getBulletActive() {
        return bulletActive;
    }
}
