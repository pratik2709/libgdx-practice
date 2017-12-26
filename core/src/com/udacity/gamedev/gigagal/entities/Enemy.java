package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Util;

public class Enemy {

    Platform platform;
    Vector2 enemyPosition;

    public Enemy(Platform platform){
        this.platform = platform;
        // initialize platform variable
        enemyPosition = new Vector2(platform.left, platform.top + Constants.ENEMY_CENTER.y);
    }

    public void update(float delta){

    }

    public void render(SpriteBatch batch){
        Util.drawTextureRegion(batch, Assets.instance.enemyAssets.enemyAtlasRegion,
        enemyPosition, Constants.ENEMY_CENTER
        );
    }
}
