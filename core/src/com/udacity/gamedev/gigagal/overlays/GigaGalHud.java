package com.udacity.gamedev.gigagal.overlays;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.util.Constants;

public class GigaGalHud {
    public final Viewport viewport;
    final BitmapFont font;

    public GigaGalHud(){
        viewport = new ExtendViewport(Constants.HUD_VIEWPORT_SIZE, Constants.HUD_VIEWPORT_SIZE);
        font = new BitmapFont();
    }

    public void render(SpriteBatch batch){
        //??
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        font.draw(batch, "Testing",
                viewport.getWorldWidth()/2, viewport.getWorldHeight()/2);
        batch.end();
    }
}
