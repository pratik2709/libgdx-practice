package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.gamedev.gigagal.entities.Enemy;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.entities.Platform;


public class Level {
    GigaGal gigaGal;
    Array<Platform> platformArray;
    private DelayedRemovalArray<Enemy> enemies;

    public Level() {
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

        gigaGal = new GigaGal(new Vector2(80, 110));
        enemies = new DelayedRemovalArray<Enemy>();
        enemies.add(new Enemy(new Platform(15, 100, 30, 20)));
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        for (Platform platform : platformArray) {
            platform.render(batch);
        }
        //add an enemy
        for(Enemy enemy: enemies){
            enemy.render(batch);
        }
        batch.end();
        gigaGal.render(batch);

    }

    public void update(float delta) {

        gigaGal.update(delta, platformArray);

        for(int i = 0; i < enemies.size; i++){
            Enemy enemy = enemies.get(i);
            enemy.update(delta);
        }
    }
}
