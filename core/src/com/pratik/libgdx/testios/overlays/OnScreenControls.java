package com.pratik.libgdx.testios.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pratik.libgdx.testios.Level;
import com.pratik.libgdx.testios.entities.GigaGal;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Util;

public class OnScreenControls extends InputAdapter {
    public final Viewport viewport;
    public GigaGal gigaGal;
    private Level level;
    private Vector2 moveLeftCenter = new Vector2();
    private Vector2 moveRightCenter = new Vector2();
    private Vector2 shootCenter = new Vector2();
    private Vector2 jumpCenter = new Vector2();
    private int moveLeftPointer;
    private int moveRightPointer;
    private int jumpPointer;

    public OnScreenControls(Level level){
        this.level = level;
        gigaGal = level.getGigaGal();
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


    //pointer actually points to the event
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        //convert from screen to world co-ordinates
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX,screenY));

        //dst is for subtract (shootcenter - viewportposition)
        if(viewportPosition.dst(shootCenter) < Constants.BUTTON_RADIUS){
            gigaGal.handleShooting();
        }
        else if(viewportPosition.dst(jumpCenter) < Constants.BUTTON_RADIUS){
            jumpPointer = pointer;
            gigaGal.jumpButtonPressed = true;
        }
        else if(viewportPosition.dst(moveLeftCenter) < Constants.BUTTON_RADIUS){
            moveLeftPointer = pointer;
            gigaGal.leftButtonPressed = true;
        }
        else if(viewportPosition.dst(moveRightCenter) < Constants.BUTTON_RADIUS){
            moveRightPointer = pointer;
            gigaGal.rightButtonPressed = true;
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));
        //left button touched and touch is dragged to right button
        if(pointer == moveLeftPointer &&
                viewportPosition.dst(moveRightCenter) < Constants.BUTTON_RADIUS){
            gigaGal.leftButtonPressed = false;
            gigaGal.rightButtonPressed = true;
            moveLeftPointer = 0;
            moveRightPointer = pointer;
        }
        if(pointer == moveRightPointer &&
                viewportPosition.dst(moveLeftCenter) < Constants.BUTTON_RADIUS){
            gigaGal.leftButtonPressed = true;
            gigaGal.rightButtonPressed = false;
            moveRightPointer = 0;
            moveLeftPointer = pointer;
        }
        return super.touchDragged(screenX,screenY,pointer);
    }

    public void render(SpriteBatch batch){
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        //why in render ?
        if(!Gdx.input.isTouched(jumpPointer)){
            gigaGal.jumpButtonPressed = false;
            jumpPointer = 0;
        }
        if(!Gdx.input.isTouched(moveLeftPointer)){
            gigaGal.leftButtonPressed = false;
            moveLeftPointer = 0;
        }
        if(!Gdx.input.isTouched(moveRightPointer)){
            gigaGal.rightButtonPressed = false;
            moveRightPointer = 0;
        }

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
