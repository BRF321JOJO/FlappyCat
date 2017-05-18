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

    static boolean Ecstacyplayedonce;
    static boolean Ecstacyrequirespause;
    static boolean Heroplayedonce;
    static boolean Herorequirespause;
    static boolean InBound;

    public Whynot(SpriteBatch batch) {
        super (
                new Texture("PixelQazi.png"),
                //Sets large Player off screen
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

        int randomnumber = (int)(Math.random()*100);
        int percentchanceofHero = 15;

        //Defines InBound
        if (posx >= 0 && posx<=Constant.screenwidth) {
        InBound = true;}
        else {InBound = false;}


        //Sets Qazi on screen and stops game
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q) && !Constant.EndGame) {
            //Sends Qazi on screen
            posx = 300;
            ImQazi.play(Music.IMQAZIvolume);
            System.out.println("Lord Qazi has appeared! Press Q to pray him away");

            //Stops backgroundmusic (dramatically) and plays sound and song(only if not muted)
            if (!Music.musicmuted) {
                Music.pauseMitchiri();

                if(randomnumber>=percentchanceofHero) {
                    Music.playEcstasy();
                    Ecstacyrequirespause = true;
                } else {
                    Music.playINeedaHero();
                    Herorequirespause = true;
                }
            }
            //Stops game
            Constant.EndGame = true;
        }

        //Sets Qazi back off screen
        else if ((Gdx.input.isKeyJustPressed(Input.Keys.Q)) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            //Only works if key pressed and Player in bounds (to avoid pressing to update dead game.)
            if (CPlayer.InBound) {
                if (!Titlescreen.onscreen) {
                    if (Constant.EndGame) {
                        System.out.println("Lord Qazi has been pleased and left");
                    }
                    //Puts large Player back off screen
                    posx = Constant.Holdingarea;

                    //Controls music after Q pressed again
                    //Resumes Mitchiri if music mot muted
                    if (!Music.musicmuted) {
                        Music.resumeMitchiri();
                        //Pauses music playing depending on which on has played
                        if (Ecstacyrequirespause) {
                            Music.pauseEcstasy();
                        }
                        if (Herorequirespause) {
                            Music.pauseINeedaHero();
                        }
                    }
                    //Resumes game
                    Constant.EndGame = false;
                }
            }
        }
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
}
