package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Marla Scrub on 3/5/2017.
 */

public class Background {

    Texture texture;
    long width;
    long height;
    long posx;
    long posy;
    int velx;

    public Background() {
        texture = new Texture ("Flappybackground.png");
        width = CPipe.Rbound;
        //516 height value of screen
        height = Constant.screentop;
        posx = 0;
        //-4 is the floor
        posy = Constant.screenbottom;
        velx = 1;
    }

    public void update(float delta) {

        //Moves background left across screen
        posx -= velx;

        //Puts the picture back
        if (posx<= -width) {
            posx = 0;
        }
    }
}
