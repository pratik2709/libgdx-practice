package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;

import static com.udacity.gamedev.gigagal.util.Constants.WORLD_SIZE;

public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();
    // spritebatch -- batch quads using indices
    SpriteBatch batch;

    ExtendViewport extendViewport;

    @Override
    public void show(){
        //Assets
        Assets.instance.init();
        batch = new SpriteBatch();
        extendViewport = new ExtendViewport(WORLD_SIZE, WORLD_SIZE);

    }

    @Override
    public void resize(int width, int height){
        extendViewport.update(width, height, true);
    }

    @Override
    public void dispose(){
        //
        Assets.instance.dispose();
        batch.dispose();
    }

    @Override
    public void render(float delta){
        extendViewport.apply();
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //why?
        batch.setProjectionMatrix(extendViewport.getCamera().combined);

        batch.begin();
        batch.draw(Assets.instance.gigaGalAssets.atlasRegion.getTexture(),
                50,
                50,
                0,
                0,
                Assets.instance.gigaGalAssets.atlasRegion.getRegionWidth(),
                Assets.instance.gigaGalAssets.atlasRegion.getRegionHeight(),
                1,
                1,
                0,
                Assets.instance.gigaGalAssets.atlasRegion.getRegionX(),
                Assets.instance.gigaGalAssets.atlasRegion.getRegionY(),
                Assets.instance.gigaGalAssets.atlasRegion.getRegionWidth(),
                Assets.instance.gigaGalAssets.atlasRegion.getRegionHeight(),
                false,
                false
        );
        batch.end();
    }
}
