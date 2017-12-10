package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;

import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.udacity.gamedev.gigagal.util.Constants.GIGAGAL_EYE_HEIGHT;
import static com.udacity.gamedev.gigagal.util.Constants.GIGAGAL_MOVE_SPEED;

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();

    //add a position
    Vector2 gigagalPosition;

    public GigaGal(){
        //initialize gigagal position
        //why 20 ?
        gigagalPosition = new Vector2(20, GIGAGAL_EYE_HEIGHT);
    }

    public void render(SpriteBatch batch){
        TextureRegion region = Assets.instance.gigaGalAssets.atlasRegion;
        batch.begin();
        batch.draw(
                region.getTexture(),

                gigagalPosition.x - Constants.GIGAGAL_EYE_POSITION.x,
                gigagalPosition.y - Constants.GIGAGAL_EYE_POSITION.y,
                0,0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,1, 0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false
        );
        batch.end();
    }

    public void update(float delta){
        if(Gdx.input.isKeyPressed(RIGHT)){
            moveRight(delta);
        }
        else if(Gdx.input.isKeyPressed(LEFT)){
            moveLeft(delta);
        }
    }

    private void moveRight(float delta) {
        gigagalPosition.x += delta * GIGAGAL_MOVE_SPEED;
    }

    private void moveLeft(float delta) {
        gigagalPosition.x -= delta * GIGAGAL_MOVE_SPEED;
    }

}
