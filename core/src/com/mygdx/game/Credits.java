package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 3/22/2017.
 */
public class Credits extends Image{

    //Constants
    //(500 - 2500)
    static int pictureheight = 2000;
    static boolean InBound;

    public Credits (SpriteBatch batch) {
        super (
                new Texture("Credits.jpg"),
                Constant.Holdingarea,
                -pictureheight,
                700,
                pictureheight,
                0,
                3,
                0,
                batch
        );
    }

    public void update (float delta) {

        //Defines InBound
        if (posx <= 0 || posx >= Constant.screenwidth) {
            InBound = false;
        } else {InBound = true;}

        //Moves image on screen
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            posx = 100;
            posy = -pictureheight;
            Constant.EndGame = true;
        }

        //Moves image up when InBound
        if (InBound) {
            posy += vely;
        }

        //Sets back to Holding area once off screen
        if(posy >= Constant.screentop) {
            posx = Constant.Holdingarea;
        }
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
}
