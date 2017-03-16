package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Marla Scrub on 2/18/2017.
 */
public class Pipebot {

    //Initialize variables

    Texture texture;
    //For picture
    long width;
    long height;
    //For position
    long posx;
    long posy;
    int velx;

    //Initializes value for a pipe
    public Pipebot(long posx) {
        texture = new Texture ("pipebot.png");
        width = CPipe.width;
        height = CPipe.height;
        this.posx = posx;
        velx = CPipe.pipevel;

        posy = Math.round(CPipe.pipemin + (300 - CPipe.pipemin)*(1/(1+
         (Math.pow (Constant.Eulere, Math.random()*12-6)))) - CPipe.height);

        //posy = Math.round(Math.random()*CPipe.pipeyrandom + CPipe.pipemin - CPipe.height);
    }

//Methods
    public void update(float delta) {

        //Moves pipe left across screen
        posx -= velx;

        if (posx<=CPipe.Lbound) {
            posx = CPipe.Rbound;

            posy = Math.round(CPipe.pipemin + (300 - CPipe.pipemin)*(1/(1+
             (Math.pow (Constant.Eulere, Math.random()*12-6)))) - CPipe.height);

            //posy = Math.round(Math.random()*CPipe.pipeyrandom + CPipe.pipemin - CPipe.height);
        }
    }
}

