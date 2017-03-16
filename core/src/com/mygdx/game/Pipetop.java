package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Marla Scrub on 2/19/2017.
 */
public class Pipetop {

    Texture texture;
    //For picture
    long width;
    long height;
    //For position
    long posx;
    long posy;
    int velx;

    public Pipetop(long posx, long posy) {
        texture = new Texture ("pipetop.png");
        width = CPipe.width;
        height = CPipe.height;
        this.posx = posx;
        this.posy = posy;
        velx = CPipe.pipevel;
    }

    public void update(float delta) {
        posx -= velx;

        if (posx<= CPipe.Lbound) {
            posx = CPipe.Rbound;

            //Posy of pipetop is determined in Gamescreen update
        }
    }
}