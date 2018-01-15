package com.pratik.libgdx.testios;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GigaGalGame extends Game {
    SpriteBatch batch;
    Texture img;

    @Override
    public void create() {
        setScreen(new GameplayScreen());
    }

}
