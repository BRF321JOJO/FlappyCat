package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 2/18/2017.
 */
public class Qazi extends Entity{

    Sound Wingflap = Gdx.audio.newSound(Gdx.files.internal("Wingflap.mp3"));
    Sound Dead = Gdx.audio.newSound(Gdx.files.internal("Dead.mp3"));

    public Qazi(SpriteBatch batch) {
        super(
                new Texture("Salsacat.png"),
                100,
                CQazi.startposy,
                //(45 - 60) Qazi: width (50), height (50)
                // Salsacat (67), height(67)
                67,
                67,
                0,
                //Initial velocity
                CQazi.velyconstant,
                0,
                batch
        );
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
            vely = CQazi.velyconstant;

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
            CQazi.InBound = false;
        } else {CQazi.InBound = true;}

        //Stops image movement at ceiling and floor
        if (!CQazi.InBound) {
            vely = 0;
            Constant.EndGame = true;
            //Makes it so only do action once after hit
            if (!CQazi.dead) {
                Dead.play(1.0f);
                CQazi.dead = true;
            }
        }
    }

    @Override
    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }
    @Override
    public void handleCollision(Entity e) {
        Constant.EndGame = true;
        //Makes it so only do action once after hit pipe.
        if (!CQazi.dead) {
            Dead.play(1.0f);
            CQazi.dead = true;
        }
        CQazi.InBound = false;
    }
}
