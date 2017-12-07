package com.pratik.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

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
    }
}
