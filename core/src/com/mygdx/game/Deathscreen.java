package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Marla Scrub on 3/19/2017.
 */
public class Deathscreen {
    Texture texture;
    long width;
    long height;
    long posx;
    long posy;

    static boolean onscreen;

    public Deathscreen() {
        texture = new Texture("Deathscreen.png");
        width = 500;
        height = 50;
        posx = Constant.Holdingarea;
        posy = 350;
    }

    public void update(float delta) {

        //Defines onscreen
        if (posx >=0 && posx <=CPipe.Rbound) {
            onscreen = true;
        } else {onscreen = false;}


        //Defines when image should be on-screen
        if (CQazi.dead) {
            posx = 200;
        }

        //Moves image back
        if (Gdx.input.isKeyJustPressed(Input.Keys.T) && Constant.EndGame && onscreen) {
            posx = Constant.Holdingarea;
        }
    }
}