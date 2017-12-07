package com.pratik.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.io.*;

public class TestGame extends ApplicationAdapter {

	public static final String TAG = TestGame.class.getName();
	private static final Color UDACITY_ORANGE = new Color(228.0f/225.0f, 127.0f/225.0f, 57.0f/225.0f, 1.0f);
	private static final float WORLD_SIZE = 100.0f;
	private static final float LOGO_SIZE = WORLD_SIZE * 0.5f;
	ExtendViewport viewport;

	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
//		viewport = new ExtendViewport(WORLD_SIZE, WORLD_SIZE);
		batch = new SpriteBatch();
		img = new Texture("udacity_logo_white.png");
		Gdx.app.log(TAG, "we are running on" + Gdx.app.getType());

		File fileRef = new File("punchline");
		try{

			FileHandle f = Gdx.files.internal("punchline");
			String e = f.readString();

			/*
			InputStream is = new FileInputStream("punchline");
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));

			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();
			sb.append(line);
			String fileAsString = sb.toString();
			*/
			Gdx.app.log(TAG, "Decrypted string" + e);

		} catch (Exception ex){

		}
	}

	@Override
	public void render () {
//		viewport.apply();
		Gdx.gl.glClearColor(UDACITY_ORANGE.r, UDACITY_ORANGE.g, UDACITY_ORANGE.b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.setProjectionMatrix(viewport.getCamera().combined);
		batch.begin();
		batch.draw(img, 0,0, LOGO_SIZE, LOGO_SIZE);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
