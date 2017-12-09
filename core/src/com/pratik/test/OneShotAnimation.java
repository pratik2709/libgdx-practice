package com.pratik.test;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class OneShotAnimation {
    public final Vector2 position;
    private final Animation<TextureRegion> animation;
    private final long startTimeNanos;

    public OneShotAnimation(Animation<TextureRegion> animation, Vector2 position, long startTimeNanos) {
        this.animation = animation;
        this.position = position;
        this.startTimeNanos = startTimeNanos;
    }

    private float elapsedTime() {
        return MathUtils.nanoToSec * (TimeUtils.nanoTime() - startTimeNanos);
    }

    public TextureRegion getFrame() {
        return animation.getKeyFrame(elapsedTime());
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(elapsedTime());
    }
}
