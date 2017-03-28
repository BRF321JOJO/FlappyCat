package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 3/8/2017.
 */
public class Whynot extends Image{

    Sound ImQazi = Gdx.audio.newSound(Gdx.files.internal("ImQazi.mp3"));

    public Whynot(SpriteBatch batch) {
        super (
                new Texture("PixelQazi.png"),
                //Sets large Qazi off screen
                Constant.Holdingarea,
                10,
                500,
                500,
                0,
                0,
                0,
                batch
        );
    }

    public void update(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q) && !Constant.EndGame) {
            //Sends Qazi on screen
            posx = 300;
            System.out.println("Lord Qazi has appeared! Press Q to pray him away");
            ImQazi.play(1.0f);
            Constant.EndGame = true;
        }

        //Only works if key pressed and Qazi in bounds (to avoid pressing to update dead game.)
        else if ((Gdx.input.isKeyJustPressed(Input.Keys.Q)) && CQazi.InBound) {
            if (!Titlescreen.onscreen) {
                //Puts large Qazi back off screen
                posx = Constant.Holdingarea;
                if (Constant.EndGame) {
                    System.out.println("Lord Qazi has been pleased and left");
                }
                Constant.EndGame = false;
            }
        }
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
}
