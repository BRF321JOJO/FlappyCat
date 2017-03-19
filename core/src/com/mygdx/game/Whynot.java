package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Marla Scrub on 3/8/2017.
 */
public class Whynot {

    Texture texture;
    long width;
    long height;
    long posx;
    long posy;
    int velx;

    Sound ImQazi = Gdx.audio.newSound(Gdx.files.internal("ImQazi.mp3"));

    //Using principal of whynot, can make a pause screen by spawning in a picture on screen when paused and back off.

    public Whynot() {
        texture = new Texture("PixelQazi.png");
        width = 500;
        height = 500;
        //Sets large Qazi off screen
        posx = Constant.Holdingarea;
        posy = 10;
        //Initial velocity, for moving Qazi
        velx = 0;
    }

    public void update(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            //Sends Qazi on screen
            posx = 300;
            if(!Constant.EndGame) {
                System.out.println("Lord Qazi has appeared" + "\n" + "Press Z to pray him away");
            }
            ImQazi.play(1.0f);
            Constant.EndGame = true;

            //Could also prevent Qazi from spawning if game ends, but will not for now

            //Moves Qazi left.
            //posx -= velx;
        }

        //Only works if key pressed and Qazi in bounds (to avoid pressing to update dead game.)
        if ((Gdx.input.isKeyJustPressed(Input.Keys.Z)) && Qazi.InBound) {
            //Puts large Qazi back off screen
            posx = Constant.Holdingarea;
            if (Constant.EndGame) {
                System.out.println("Lord Qazi has been pleased and left");
            }
            Constant.EndGame = false;
        }
    }
}
