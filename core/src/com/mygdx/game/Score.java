package com.mygdx.game;

/**
 * Created by Marla Scrub on 3/8/2017.
 */
public class Score {

    static int scorevalue = 0;
    static int highscorevalue = 0;
    static boolean statedonce = false;


    public void update(float delta) {

        if (scorevalue >= 10 && !statedonce && Constant.EndGame) {
            System.out.println("Wow, you did pretty well!");
            statedonce = true;
        }

        if (scorevalue > highscorevalue) {
            highscorevalue++;
            System.out.println("That's the highscore!");
        }

        //When score value increases is in GameScreen update method
    }
}
