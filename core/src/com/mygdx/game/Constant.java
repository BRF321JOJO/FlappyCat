package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Marla Scrub on 3/8/2017.
 */

public class Constant {

    //Screen height and V_Height and such equalities have direct correlation. 100 to one is 100 to other.
    //1000 V_Height = 516 screentop.

   //Experimentally found in Background (screenheight is absolute distance between top and bottom)
    //Usually -4
    public static int screenbottom = -4;
    //Usually 516
    //(Less than 656)
    public static int screentop = 516;
    //Usually 520
    public static int screenheight = 520;
    //Usually 900
    public static int screenwidth = 900;
    //Usually 1000
    public static int Holdingarea = 1000;

    //int counter for code not every frame
    static int framecounter = 0;


    Sound Cats = Gdx.audio.newSound(Gdx.files.internal("Cats.mp3"));


    //Boolean that helps tell when game ends (Updates stop)
    static boolean EndGame;


    public void update(float delta) {

        //Increases counter value for delayed actions
        framecounter++;

        //Pauses game
        if (Gdx.input.isKeyJustPressed(Input.Keys.P) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (!EndGame) {
                System.out.println("Game paused");
            }
            EndGame = true;
        }

        //Resumes Game
        //Prevents update to game if Player out of bounds (other function will continue game after death)
        //or if it is the title screen
        if (Gdx.input.isKeyJustPressed(Input.Keys.R) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (CPlayer.InBound && EndGame) {
                if (!Titlescreen.onscreen) {
                    System.out.println("Game resumed");
                }
                EndGame = false;
            }
        }

        //Secrets
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            System.out.println(":3");
            Cats.play(Music.catvolume);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            System.out.println("Euler's number (e) is about " + Math.E);
        }
    }
}


