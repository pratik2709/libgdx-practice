package com.pratik.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG;
import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL;

public class Animations extends ApplicationAdapter{
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
    public void create(){
        batch = new SpriteBatch();
        extendViewport = new ExtendViewport(100,100);

        //set start time
        startTime = System.nanoTime();

        Array<TextureRegion> walkLoopTextures = new Array<TextureRegion>();

        //add texture region to arrays
        walkLoopTextures.add(new TextureRegion(new Texture("walk-1-right.png")));
        walkLoopTextures.add(new TextureRegion(new Texture("walk-2-right.png")));
        walkLoopTextures.add(new TextureRegion(new Texture("walk-3-right.png")));

        //add walkloop animation
        walkLoop = new Animation(WALK_LOOP_FRAME_DURATION, walkLoopTextures ,LOOP_PINGPONG);

        Array<TextureRegion> explosionTextures = new Array<TextureRegion>();
        explosionTextures.add(new TextureRegion(new Texture("explosion-large.png")));
        explosionTextures.add(new TextureRegion(new Texture("explosion-medium.png")));
        explosionTextures.add(new TextureRegion(new Texture("explosion-small.png")));
        explosion = new Animation(EXPLOSION_FRAME_DURATION, explosionTextures, NORMAL);
        explosions = new DelayedRemovalArray<OneShotAnimation>();
    }

    @Override
    public void resize(){

    }
}
