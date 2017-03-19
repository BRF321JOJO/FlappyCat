package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Marla Scrub on 3/10/2017.
 */
public class Laser {

    Texture texture;
    int width;
    int height;
    long posx;
    long posy;
    int velx;

    Sound Laser = Gdx.audio.newSound(Gdx.files.internal("Pew.mp3"));

    static boolean InBound;

    public Laser() {
        texture = new Texture("laser.png");
        width = 50;
        height = 15;
        posx = Constant.Holdingarea;
        posy = 300;
        //(15 - 50)
        velx = 20;
    }

    public void update(float delta) {

        //Defines InBound
        if (posx <=0 || posx >=CPipe.Rbound) {
            InBound = false;
        } else {InBound = true;}


        //Sets laser at 100 (only if off screen)
        if (Gdx.input.isKeyJustPressed(Input.Keys.L) && !InBound) {
            posx = 100;
            Laser.play(1.0f);
            System.out.println("Pew, you shot a laser!");
        }

        //Moves laser right (only if on screen)
        else if (InBound) {
            posx += velx;
        }

        //Puts laser back to hold area once off screen
        if (posx == CPipe.Rbound) {
            posx = Constant.Holdingarea;
        }
        //posy of laser depends on Qazi posy. In GameScreen update method.
    }
}
