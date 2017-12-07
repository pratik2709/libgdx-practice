package com.pratik.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG;
import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL;

public class Animations extends ApplicationAdapter {
    private static final float EXPLOSION_SPAWN_RATE = 20;
    private static final float EXPLOSION_FRAME_DURATION = 0.1f;
    private static final float WALK_LOOP_FRAME_DURATION = 0.1f;

    //drawing textures on quads (quadilaterals)
    SpriteBatch batch;
    //the world is scaled to fill the viewport (no black bars)
    ExtendViewport extendViewport;

    Animation walkLoop;
    long startTime;

    Animation explosion;
    DelayedRemovalArray<OneShotAnimation> explosions;

    @Override
    public void create() {
        batch = new SpriteBatch();
        extendViewport = new ExtendViewport(100, 100);

        //set start time
        startTime = System.nanoTime();

        Array<TextureRegion> walkLoopTextures = new Array<TextureRegion>();

        //add texture region to arrays
        walkLoopTextures.add(new TextureRegion(new Texture("walk-1-right.png")));
        walkLoopTextures.add(new TextureRegion(new Texture("walk-2-right.png")));
        walkLoopTextures.add(new TextureRegion(new Texture("walk-3-right.png")));

        //add walkloop animation
        walkLoop = new Animation(WALK_LOOP_FRAME_DURATION, walkLoopTextures, LOOP_PINGPONG);

        Array<TextureRegion> explosionTextures = new Array<TextureRegion>();
        explosionTextures.add(new TextureRegion(new Texture("explosion-large.png")));
        explosionTextures.add(new TextureRegion(new Texture("explosion-medium.png")));
        explosionTextures.add(new TextureRegion(new Texture("explosion-small.png")));
        explosion = new Animation(EXPLOSION_FRAME_DURATION, explosionTextures, NORMAL);
        explosions = new DelayedRemovalArray<OneShotAnimation>();
    }

    @Override
    //whenever resize event occurs
    //resize viewport and update camera
    public void resize(int width, int height) {
        extendViewport.update(width, height);
    }

    @Override
    public void render() {
        updateExplosions();
    }

    private void updateExplosions() {
        explosions.begin();
        for (int i = 0; i < explosions.size; i++) {
            if (explosions.get(i).isAnimationFinished()) {
                explosions.removeIndex(i);
            }
        }
        explosions.end();

        //random explosion spawn
        //time span between current and last frame
        if (MathUtils.random() < Gdx.graphics.getDeltaTime() * EXPLOSION_SPAWN_RATE) {
            //2D vector
            Vector2 position = new Vector2(MathUtils.random(extendViewport.getWorldWidth()),
                    MathUtils.random(extendViewport.getWorldHeight()));
            explosions.add(new OneShotAnimation());
        }

    }


}
