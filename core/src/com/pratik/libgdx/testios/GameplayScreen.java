package com.pratik.libgdx.testios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.pratik.libgdx.testios.overlays.GigaGalHud;
import com.pratik.libgdx.testios.overlays.OnScreenControls;
import com.pratik.libgdx.testios.overlays.VictoryOverlay;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.ChaseCam;
import com.pratik.libgdx.testios.util.Util;

public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();

    //level
    Level level;

    // spritebatch -- batch quads using indices
    SpriteBatch batch;

    ExtendViewport extendViewport;

    //chase cam
    private ChaseCam chaseCamera;

    private GigaGalHud gigaGalHud;

    private OnScreenControls onScreenControls;

    //overlays
    long levelEndOverlayStartTime;
    private VictoryOverlay victoryOverlay;

    @Override
    public void show(){
        extendViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        //Assets
        AssetManager am = new AssetManager();
        Assets.instance.init(am);
        level = LevelLoader.load("Level1", extendViewport);
        batch = new SpriteBatch();
        chaseCamera = new ChaseCam(extendViewport.getCamera(), level.gigaGal);
        gigaGalHud = new GigaGalHud();
        onScreenControls = new OnScreenControls(level);
        victoryOverlay = new VictoryOverlay();
        if(onMobile()){
            Gdx.input.setInputProcessor(onScreenControls);
        }

    }

    private boolean onMobile() {
//        return Gdx.app.getType() == Application.ApplicationType.Android ||
//                Gdx.app.getType() == Application.ApplicationType.iOS;
        return true;
    }

    @Override
    public void resize(int width, int height){
        gigaGalHud.viewport.update(width, height, true);
        extendViewport.update(width, height, true);
        onScreenControls.viewport.update(width, height, true);
        victoryOverlay.viewport.update(width, height,true);
    }

    @Override
    public void dispose(){
        //
        Assets.instance.dispose();
        batch.dispose();
    }

    @Override
    public void render(float delta){
        level.update(delta);
        chaseCamera.update(delta);
        extendViewport.apply();
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //why?
        batch.setProjectionMatrix(extendViewport.getCamera().combined);
        level.render(batch);
        gigaGalHud.render(batch,level.gigaGal.getLives(),
                level.gigaGal.getAmmoCount(),
                level.score);
        renderLevelEndOverlays(batch);
        if(onMobile()){
            onScreenControls.render(batch);
        }
    }

    private void renderLevelEndOverlays(SpriteBatch batch) {
        if(level.victory){
            if(levelEndOverlayStartTime == 0){
                levelEndOverlayStartTime = TimeUtils.nanoTime();
                victoryOverlay.init(level);

            }
            victoryOverlay.render(batch);
            //render
            if(Util.secondsSince(levelEndOverlayStartTime) > Constants.LEVEL_END_DURATION){
                levelEndOverlayStartTime = 0;
                levelComplete();
            }
        }
    }

    private void levelComplete() {
        startNewLevel();
    }

    private void startNewLevel(){
        System.out.println("Start new level");
    }


}
