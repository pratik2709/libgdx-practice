package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.entities.*;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Enums;
import com.udacity.gamedev.gigagal.util.Util;


public class Level {
    GigaGal gigaGal;
    Array<Platform> platformArray;
    long explosionStartTime;
    private DelayedRemovalArray<Enemy> enemies;
    private DelayedRemovalArray<Bullet> bullets;
    private DelayedRemovalArray<Explosion> explosions;
    private Viewport viewport;


    public Level(Viewport viewport) {
        this.viewport = viewport;
        platformArray = new Array<Platform>();
        addDebugPlatform();
    }

    private void addDebugPlatform() {
        platformArray.add(new Platform(15, 100, 30, 20));
        platformArray.add(new Platform(75, 90, 100, 65));
        platformArray.add(new Platform(35, 55, 50, 20));
        platformArray.add(new Platform(10, 20, 20, 9));
        platformArray.add(new Platform(100, 110, 30, 9));
        platformArray.add(new Platform(200, 130, 30, 40));
        platformArray.add(new Platform(150, 150, 30, 9));
        platformArray.add(new Platform(150, 180, 30, 9));
        platformArray.add(new Platform(200, 200, 9, 9));
        platformArray.add(new Platform(280, 100, 30, 9));

        gigaGal = new GigaGal(new Vector2(15, 100), this);
        enemies = new DelayedRemovalArray<Enemy>();
        enemies.add(new Enemy(new Platform(100, 110, 30, 9)));
        bullets = new DelayedRemovalArray<Bullet>();
        explosions = new DelayedRemovalArray<Explosion>();
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        for (Platform platform : platformArray) {
            platform.render(batch);
        }
        //add an enemy
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }

        //render the bullets
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }

//        Util.drawTextureRegion(batch, Assets.instance.bulletAssets.bulletRegion,
//                new Vector2(10, 10), Constants.BULLET_CENTER
//        );
//
//        Util.drawTextureRegion(batch, Assets.instance.powerupAssets.powerupRegion,
//                new Vector2(20, 20), Constants.POWERUP_CENTER
//        );
//
//        TextureRegion region = Assets.instance.explosionAssets.explosionAnimation.getKeyFrame(
//                Util.secondsSince(explosionStartTime)
//        );
//
//        Util.drawTextureRegion(batch, region,
//                new Vector2(40, 20), Constants.EXPLOSION_CENTER
//        );

        for(Explosion explosion: explosions){
            explosion.render(batch);
        }

        batch.end();
        gigaGal.render(batch);


    }

    public void update(float delta) {
        explosionStartTime = TimeUtils.nanoTime();
        gigaGal.update(delta, platformArray);

        enemies.begin();

        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(delta);
            if(enemy.healthCounter < 1){
                spawnExplosion(enemy.enemyPosition);
                enemies.removeIndex(i);
            }
        }
        enemies.end();


//        System.out.println(bullets.size);
        bullets.begin();

        for (Bullet bullet : bullets) {
            bullet.update(delta);
            if (!bullet.getBulletActive()) {
                bullets.removeValue(bullet, false);
            }
        }
        bullets.end();

        //explosions
        explosions.begin();
        for(Explosion explosion: explosions){
            if(explosion.isFinished()){
                explosions.removeValue(explosion, false);
            }
        }
        explosions.end();

    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public void spawnBullet(Vector2 position, Enums.Direction direction) {
        bullets.add(new Bullet(this, position, direction));
    }

    public void spawnExplosion(Vector2 position){
        explosions.add(new Explosion(position));
    }

    public Viewport getViewport() {

        return viewport;
    }
}
