package com.mygdx.game;

/**
 * Created by Marla Scrub on 3/8/2017.
 */
public class Score {

    static int scorevalue = 0;
    static int highscorevalue = 0;
    static boolean newhighscore;

    public void update(float delta) {

        //Increases highscore value
        if (scorevalue > highscorevalue) {
            highscorevalue++;
            newhighscore = true;
        }

        //Tells when can print highscore message (only after death, not every time increases)
        //Message resets after every restart
        if (newhighscore && CPlayer.dead) {
            System.out.println("That's the highscore!");
        }


        //Score value increases in GameScreen update method
    }
}
