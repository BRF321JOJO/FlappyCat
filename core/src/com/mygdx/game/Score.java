package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Marla Scrub on 3/8/2017.
 */
public class Score {

    static int scorevalue = 0;

    Texture texture;
    long width;
    long height;
    long posx;
    long posy;


    //Ccurrently off screen for pos.
    public Score() {
        texture = new Texture("Lilypichu.png");
        width = 50;
        height = 50;
        //usually 425
        posx = 1000;
        posy = 425;
    }

    public void update(float delta) {

        //Update in GameScreen update method

    }
}
