package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.entities.Platform;


public class Level {
    GigaGal gigaGal;
    Array<Platform> platformArray;


    public Level(){

        gigaGal = new GigaGal();
        platformArray = new Array<Platform>();
        platformArray.add(new Platform(70, 30 ,20, 20));
    }

    public void render(SpriteBatch batch){
        batch.begin();
        for(Platform platform:platformArray){
            platform.render(batch);
        }
        batch.end();
        gigaGal.render(batch);
    }

    public void update(float delta){
        gigaGal.update(delta, platformArray);
    }
}
