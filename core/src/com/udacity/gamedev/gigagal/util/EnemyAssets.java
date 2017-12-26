package com.udacity.gamedev.gigagal.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class EnemyAssets {

    //atlas region for the enemy
    public TextureAtlas.AtlasRegion enemyAtlasRegion;

    public EnemyAssets(TextureAtlas atlas){
        enemyAtlasRegion = atlas.findRegion(Constants.ENEMY);
    }
}
