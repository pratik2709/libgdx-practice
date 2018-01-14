package com.udacity.gamedev.gigagal.overlays;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Util;

public class GigaGalHud {
    public final Viewport viewport;
    final BitmapFont font;

    public GigaGalHud(){
        viewport = new ExtendViewport(Constants.HUD_VIEWPORT_SIZE, Constants.HUD_VIEWPORT_SIZE);
        font = new BitmapFont();
    }

    public void render(SpriteBatch batch, int lives, int ammo, int score){
        //??
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        font.draw(batch, Integer.toString(score),
                viewport.getWorldWidth()/2, viewport.getWorldHeight()/2);
        font.draw(batch, Integer.toString(ammo),
                viewport.getWorldWidth()/8, viewport.getWorldHeight()/2);
        for(int i = 0; i < lives; i++){
            Util.drawTextureRegion(batch,
                    Assets.instance.gigaGalAssets.standingRight,
                    new Vector2(viewport.getWorldWidth()/2 + 50*i, viewport.getWorldHeight()),
                    Constants.GIGAGAL_EYE_POSITION);
        }
        batch.end();
    }
}
