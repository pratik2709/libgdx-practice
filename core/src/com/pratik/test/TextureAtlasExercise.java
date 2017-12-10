package com.pratik.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureAtlasExercise extends ApplicationAdapter implements AssetErrorListener {

    public static final String TAG = TextureAtlasExercise.class.getName();

    private static final String ATLAS = "images/gigagal.pack.atlas";
    // notice no png
    private static final String STANDING_RIGHT = "standing-right";

    //add asset manager
    private AssetManager assetManager;
    SpriteBatch batch;

    //add atlas region for standing right sprite
    TextureAtlas.AtlasRegion standingRight;

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {

    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetManager = new AssetManager();

        //error listener
        assetManager.setErrorListener(this);

        //load texture atlas named ATLAS
        assetManager.load(ATLAS, TextureAtlas.class);

        //block until all assets loads
        assetManager.finishLoading();

        //get TextureAtlas
        TextureAtlas atlas = assetManager.get(ATLAS);

        //find a region
        standingRight = atlas.findRegion(STANDING_RIGHT);
        Gdx.app.log("ttt", "we are running on" + atlas);

    }

    @Override
    public void render() {
        //Simply set the desired clear color and then call glClear()
        // with the desired buffers to clear.
        // You are then free to render a fresh frame with new scene graphics.

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        //draw standing right region
        batch.draw(
                standingRight.getTexture(),
                0,
                0,
                0,
                0,
                standingRight.getRegionWidth(),
                standingRight.getRegionHeight(),
                1,
                1,
                0,
                standingRight.getRegionX(),
                standingRight.getRegionY(),
                standingRight.getRegionWidth(),
                standingRight.getRegionHeight(),
                false,
                false);

        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        // TODO: Dispose of the AssetManager
        assetManager.dispose();
    }
}
