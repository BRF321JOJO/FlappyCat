package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Constant;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {

		//Borderless Window
		//System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

		//Configurations for Desktop Version
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.resizable=true;
		cfg.height = Constant.screentop;
		cfg.width = Constant.screenwidth;
		cfg.foregroundFPS=60;
		cfg.vSyncEnabled=true;

		//Names java file when run
		cfg.title = "Flappy Cat";
		//Makes java image SalsaCat
		cfg.addIcon("pixelcat.jpg", Files.FileType.Internal);

		new LwjglApplication(new MyGdxGame(), cfg);

	}
}