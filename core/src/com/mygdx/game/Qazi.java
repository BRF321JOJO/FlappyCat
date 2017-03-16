package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Marla Scrub on 2/18/2017.
 */
public class Qazi {

    //Functions

    Texture texture;
    int width;
    int height;
    static int posx;
    int posy;
    int vely;

    Sound Wingflap = Gdx.audio.newSound(Gdx.files.internal("Wingflap.mp3"));

    //Helps to tell when game continue or stop when Qazi on or off screen
    static boolean InBound;

    public Qazi() {
        texture = new Texture("Salsacat.png");
        width = 67;
        height = 67;
        posx = 100;
        posy = 300;
        //Initial velocity
        vely = 12;

        //(45 - 60) Qazi: width (50), height (50)
        //Salsacat (67), height(67)
    }

//Methods

    public void update(float delta) {
        //int counter for code not every frame

//            int counter = 0;
//            counter++;
//            if counter == 3 { *Put at end of code run*
//                *Code that runs*
//            }

        //Changes y position based on vely value
        posy += vely;
        //Gdx.input.isKeyPressed(Input.Keys.SPACE)
        //This allows holding down key to take action, constantly
        //If isKeyJustPressed is used instead, then it only works each time it's pressed. Prevents holding down key to allow action.

        //Moves image up
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            //10-20. 12 is fair
            vely = 12;

            //Plays sound of flap
            Wingflap.play(1.0f);
        }

        //Moves image constantly down
        else if (posy > 0) {
            vely--;
        }

        //The ceiling should be the display height - Qazi height (to cause head to hit ceiling)
        //Use instead:
        //V_Height - a value which is experimentally the top of the screen
        //Best 489 for now (Larger moves ceiling down, less moves up)
        //*Note: Differing heights are fine. Does not require change of 489.
        long ceiling = 489 + height;

        //Defines InBound
        if (posy <= 0 || posy >= (MyGdxGame.V_HEIGHT - ceiling)) {
            InBound = false;
        } else {InBound = true;}

        //Stops image movement at ceiling and floor
        if (!InBound) {
            vely = 0;
            Constant.EndGame = true;
        }
    }
}
