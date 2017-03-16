package com.mygdx.game;

/**
 * Created by Marla Scrub on 2/19/2017.
 */
public class CPipe {

    //Constants

    //Tells the number of pipes
    static int numberofpipes = 8;
    //Tells the max value which the for statements should use when the number of pipes change
    static int numpipesmax = (numberofpipes - 1);

    //Proportions of a pipes
    static long width = 105;
    static long height = 1000;

    //Distance between oncoming pipes
    //(145 - 295) 195 good
    static long pipespace = width + 195;

    //Where pipes spawn and despawn
    //(900 - 970) 950 good
    static long Rbound = 950;

    //Lbound based on values of pipes. Equal to length of a unit of pipes.
    //(7 spaces between pipes) plus an extra space after.
    //Subtract Rbound because Lbound value is equal negative to distance from 0 to Lbound.
    //(not the absolute distance it moves from the Rbound)
    static long Lbound = -((numberofpipes*pipespace)-Rbound);

    //Distance top pipe above bottom pipe
    //Fair is at least 170
    static long pipeabove = height + 170;

    //Velocity of Pipes (moving left)
    static int pipevel = 5;

    //Min/max of pipe spawn (experimentally found)
    //Lowest value = 75
    static long pipemin = 75;

    //Heighest value (of bottom pipe) pipeyrandom(200) + pipemin(75) + pipeabove(170) = 445
    //idk???
    static long pipemax = 300;



    //The following functions aren't used, but are the defined functions for the values of the random y positions.


    //Uses Sigmoid function
    //a = lower bound. b = upper bound. x = (rand*12)-6. L = b-a. a+L(1/(1-e^(x))) = height
    //x value can be changed for different randomness
    //More extreme
    long pipeySigmoid = Math.round(CPipe.pipemin + (CPipe.pipemax - CPipe.pipemin)*(1/(1+
            (Math.pow (Constant.Eulere, Math.random()*12-6)))) - CPipe.height);

    //Calmer
    long regrandom = Math.round(Math.random()*CPipe.pipeyrandom + CPipe.pipemin - CPipe.height);


    //Keep below:
    //For old random y value version:

    //Degree of y randomness value pipes spawn at
    //200 good
    static long pipeyrandom = 200;
}
