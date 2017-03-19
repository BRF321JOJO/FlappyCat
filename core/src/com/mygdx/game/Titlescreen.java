package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Marla Scrub on 3/19/2017.
 */
public class Titlescreen {
    Texture texture;
    long width;
    long height;
    long posx;
    long posy;

    static boolean onscreen;

    public Titlescreen() {
        texture = new Texture("Titlescreen.png");
        width = 500;
        height = 50;
        posx = 200;
        posy = 350;
    }

    public void update(float delta) {

        //Defines onscreen
        if (posx >=0 && posx <=CPipe.Rbound) {
            onscreen = true;
        } else {onscreen = false;}

        //Stops game if image is on-screen
        if (onscreen) {
            Constant.EndGame = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && Constant.EndGame && onscreen) {
            posx = Constant.Holdingarea;
            Constant.EndGame = false;
        }
    }
}
