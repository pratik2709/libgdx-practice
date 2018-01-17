package com.pratik.libgdx.testios.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pratik.libgdx.testios.Level;
import com.pratik.libgdx.testios.entities.Explosion;
import com.pratik.libgdx.testios.util.Assets;
import com.pratik.libgdx.testios.util.Constants;
import com.pratik.libgdx.testios.util.Util;

public class VictoryOverlay {
    public static final String TAG = VictoryOverlay.class.getName();
    public final Viewport viewport;
    final BitmapFont font;
    DelayedRemovalArray<Explosion> explosions;
    float textWidth;

    public VictoryOverlay() {
        this.viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        font = new BitmapFont(Gdx.files.internal(Constants.FONT_FILE));
        font.getData().setScale(1);
        GlyphLayout layout = new GlyphLayout(font, "You won!");
        textWidth = layout.width;
    }

    public void init(Level level) {
        explosions = new DelayedRemovalArray<Explosion>(Constants.EXPLOSION_COUNT);
        for (int i = 0; i < Constants.EXPLOSION_COUNT; i++) {
            explosions.add(new Explosion(
                    new Vector2(MathUtils.random(viewport.getWorldWidth() ),
                            MathUtils.random(viewport.getWorldHeight()))

            ));
        }

    }

    public void render(SpriteBatch batch) {
        explosions.begin();
        for (Explosion explosion : explosions) {
            if (explosion.isFinished()) {
                explosions.removeValue(explosion, false);
            }
        }
        explosions.end();
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        for (Explosion explosion : explosions) {
            explosion.render(batch);
        }
        font.draw(batch, "You Won!",
                viewport.getWorldWidth() / 2 - textWidth / 2,
                viewport.getWorldHeight() / 2 - textWidth / 4);
        batch.end();
    }
}
