package com.mygdx.game;

/**
 * Created by Marla Scrub on 3/19/2017.
 */
public class CQazi {

    //12 works traditionally. 14 for lighter gravity
    static int velyconstant = 14;
    static int startposy = 300;

    //Prevents acctions from constantly happening if colliding with objects
    static boolean dead = false;

    //Helps to tell when game continue or stop when Qazi on or off screen
    static boolean InBound;
}
