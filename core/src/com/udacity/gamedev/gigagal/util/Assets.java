package com.udacity.gamedev.gigagal.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();

    //singleton ?
    public static final Assets instance = new Assets();

    public GigaGalAssets gigaGalAssets;

    public PlatformAssets platformAssets;

    public EnemyAssets enemyAssets;

    public BulletAssets bulletAssets;
    public ExplosionAssets explosionAssets;
    public PowerupAssets powerupAssets;
    public ExitPortalAssets exitPortalAssets;

    private AssetManager assetManager;


    private Assets(){
    }

    public void init(AssetManager assetManager){
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        gigaGalAssets = new GigaGalAssets(atlas);
        platformAssets = new PlatformAssets(atlas);
        enemyAssets = new EnemyAssets(atlas);
        bulletAssets = new BulletAssets(atlas);
        explosionAssets = new ExplosionAssets(atlas);
        powerupAssets = new PowerupAssets(atlas);
        exitPortalAssets = new ExitPortalAssets(atlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Could not load asset::" + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        //releases all resources of the object
        assetManager.dispose();
    }
}
