package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Constant;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {

		//Borderless Window
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

		//Configurations for Desktop Version
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.resizable=false;
		cfg.height = Constant.screentop;
		cfg.width = Constant.screenwidth;
		cfg.foregroundFPS=60;
		cfg.vSyncEnabled=true;

		new LwjglApplication(new MyGdxGame(), cfg);

	}
}