package com.mygdx.game.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.GameLluviaMenu;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("GameLluviaMenu");
		config.setWindowedMode(1600, 960);
		new Lwjgl3Application(new GameLluviaMenu(), config);
	}
}

