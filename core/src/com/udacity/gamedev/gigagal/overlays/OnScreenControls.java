package com.udacity.gamedev.gigagal.overlays;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Util;

public class OnScreenControls {
    public final Viewport viewport;
    public GigaGal gigaGal;
    private Vector2 moveLeftCenter = new Vector2();
    private Vector2 moveRightCenter = new Vector2();
    private Vector2 shootCenter = new Vector2();
    private Vector2 jumpCenter = new Vector2();

    public OnScreenControls(){
        viewport = new ExtendViewport(Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE,
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE);
        recalculateButtonPositions();

    }

    private void recalculateButtonPositions() {
        moveLeftCenter.set(Constants.BUTTON_RADIUS*3/4, Constants.BUTTON_RADIUS);
        moveRightCenter.set(Constants.BUTTON_RADIUS*2, Constants.BUTTON_RADIUS*3/4);
        shootCenter.set(Constants.BUTTON_RADIUS * 6, Constants.BUTTON_RADIUS*3/4);
        jumpCenter.set(Constants.BUTTON_RADIUS*3/4 * 6, Constants.BUTTON_RADIUS);
    }

    public void render(SpriteBatch batch){
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        Util.drawTextureRegion(batch,
                Assets.instance.onScreenControlsAssets.moveLeftButton,
                moveLeftCenter,
                Constants.BUTTON_CENTER);

        Util.drawTextureRegion(batch,
                Assets.instance.onScreenControlsAssets.moveRightButton,
                moveRightCenter,
                Constants.BUTTON_CENTER);

        Util.drawTextureRegion(batch,
                Assets.instance.onScreenControlsAssets.shootButton,
                shootCenter,
                Constants.BUTTON_CENTER);

        Util.drawTextureRegion(batch,
                Assets.instance.onScreenControlsAssets.jumpButton,
                jumpCenter,
                Constants.BUTTON_CENTER);
        batch.end();
    }
}
