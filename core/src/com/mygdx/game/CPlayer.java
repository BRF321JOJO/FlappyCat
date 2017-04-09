package com.mygdx.game;

/**
 * Created by Marla Scrub on 3/19/2017.
 */
public class CPlayer {

    //Proportions of player
    // Salsacat (67), height(67)
    static int playerwidth = 55;
    static int playerheight = 55;

    //Controls gravity (15 good)
    static int velyconstant = 14;
    //Where player starts on screen
    static int startposy = 250;

    //Prevents actions from constantly happening if colliding with objects
    static boolean dead = false;

    //Helps to tell when game continue or stop when player on or off screen
    static boolean InBound;
}
