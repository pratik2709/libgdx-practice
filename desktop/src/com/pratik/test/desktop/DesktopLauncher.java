package com.pratik.test.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pratik.test.Animations;
import com.pratik.test.NinePatchDemo;
import com.pratik.test.TestGame;
import com.udacity.gamedev.gigagal.GigaGalGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GigaGalGame(), config);
	}
}
