package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 3/10/2017.
 */
public class Laser extends Entity{

    Sound Laser = Gdx.audio.newSound(Gdx.files.internal("Pew.mp3"));

    static boolean InBound;

    public Laser(SpriteBatch batch) {
        super (
                new Texture("laser.png"),
                Constant.Holdingarea,
                300,
                50,
                15,
                //(15 - 50)
                20,
                0,
                0,
                batch
        );
    }

    public void update(float delta) {

        //Defines InBound
        if (posx <=0 || posx >=CPipe.Rbound) {
            InBound = false;
        } else {InBound = true;}


        //Sets laser at 100 (only if off screen)
        if (Gdx.input.isKeyJustPressed(Input.Keys.L) && !InBound) {
            //posy of laser depends on Player posy. [In GameScreen, update method]
            posx = 100;
            Laser.play(Music.laservolume);
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
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    public void handleCollision(Entity e) {
        //Moves laser off screen to be able to be shot again (only if in screen though)
        if (InBound) {
            posx = Constant.Holdingarea;
        }
    }
}
