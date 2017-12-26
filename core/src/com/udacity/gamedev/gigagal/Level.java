package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.entities.Platform;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;


public class Level {
    GigaGal gigaGal;
    Array<Platform> platformArray;


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
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        for (Platform platform : platformArray) {
            platform.render(batch);
        }
        //add an enemy
        batch.draw(Assets.instance.enemyAssets.enemyAtlasRegion.getTexture(),
                100 + Constants.ENEMY_CENTER.x,
                100 + Constants.ENEMY_CENTER.y,
                0,
                0,
                Assets.instance.enemyAssets.enemyAtlasRegion.getRegionWidth(),
                Assets.instance.enemyAssets.enemyAtlasRegion.getRegionHeight(),
                1,
                1,
                0,
                Assets.instance.enemyAssets.enemyAtlasRegion.getRegionX(),
                Assets.instance.enemyAssets.enemyAtlasRegion.getRegionY(),
                Assets.instance.enemyAssets.enemyAtlasRegion.getRegionWidth(),
                Assets.instance.enemyAssets.enemyAtlasRegion.getRegionHeight(),
                false,
                false);
        batch.end();
        gigaGal.render(batch);

    }

    public void update(float delta) {
        gigaGal.update(delta, platformArray);
    }
}
