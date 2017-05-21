package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Marla Scrub on 3/5/2017.
 */

public class Background extends Image{

    //(1 is reg. 2 is max)
    int everyxframes = 2;


    public Background (SpriteBatch batch) {
        super(
                new Texture ("Flappybackground.png"),
                0,
                //-4 is the floor
                Constant.screenbottom,
                Constant.screenwidth,
                //516 height value of screen
                Constant.screenheight,
                1,
                0,
                0,
                batch
        );
    }

    public void update(float delta) {

        //Background moves every x frames
        if (Constant.framecounter % everyxframes == 0) {

            //Moves background left across screen
            posx -= velx;
        }

        //Puts the picture back
        if (posx<= -width) {
            posx = 0;
        }
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
}
