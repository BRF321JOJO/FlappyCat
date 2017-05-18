package com.mygdx.game;

/**
 * Created by Marla Scrub on 3/8/2017.
 */
public class Score {

    static int scorevalue = 0;
    static int highscorevalue = 0;
    static boolean once = false;


    public void update(float delta) {

        if (scorevalue > highscorevalue) {
            highscorevalue++;
            System.out.println("That's the highscore!");
        }

        //Score value increases in GameScreen update method
    }
}
