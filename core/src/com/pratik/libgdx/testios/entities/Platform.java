package com.pratik.libgdx.testios.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pratik.libgdx.testios.util.Assets;

public class Platform {

    public float top;
    public float bottom;
    public float left;
    public float right;

    public Platform(float left, float top, float width, float height){
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
    }

    public void render(SpriteBatch batch){
        float width = right - left;
        float height = top - bottom;

        //?? wtf is left bottom
        Assets.instance.platformAssets.platformNinePatch.draw(batch, left - 1, bottom - 1,
                width + 2, height + 2);
    }

}
