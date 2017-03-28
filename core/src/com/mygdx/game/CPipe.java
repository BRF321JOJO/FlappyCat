package com.mygdx.game;

/**
 * Created by Marla Scrub on 2/19/2017.
 */
public class CPipe {

    //Constants

    //Tells the number of pipes
    static int numberofpipes = 3;
    //Tells the max value which the for statements should use
    static int numpipesmax = (numberofpipes - 1);

    //Proportions of a pipes
    static int width = 105;
    static int height = 1000;

    //Distance between oncoming pipes
    //At least 195
    static int pipespace = width + 295;

    //Where pipes spawn and despawn
    //(900 - 970) 950 good
    static int Rbound = 950;

    //Lbound based on values of pipes. Equal to length of a unit of pipes.
    //(7 spaces between pipes) plus an extra space after.
    //Subtract Rbound because Lbound value is equal negative to distance from 0 to Lbound.
    //(not the absolute distance it moves from the Rbound)
    static int Lbound = -((numberofpipes*pipespace)-Rbound);

    //Distance top pipe above bottom pipe
    //Fair is at least 170: (For vely = 12)
    //Fair is at least 200: (For vely = 14)
    //(200 - 250)
    static int pipeabove = height + 200;

    //Velocity of Pipes (moving left)
    static int pipevel = 5;

    //Min/max of pipe spawn (experimentally found)
    //Lowest value = 75
    static int pipemin = 75;

    //Heighest value (of bottom pipe) pipeyrandom(200) + pipemin(75) + pipeabove(170) = 445
    //idk???
    static int pipemax = 300;




    //The following functions aren't used directly,
    // but are the defined functions for the values of the random y positions.


    //Uses Sigmoid function
    //a = lower bound. b = upper bound. x = (rand*12)-6. L = b-a. a+L(1/(1-e^(x))) = height
    //x value can be changed for different randomness
    //More extreme
    int pipeposySigmoid = (int)(Math.round(CPipe.pipemin + (CPipe.pipemax - CPipe.pipemin)*(1/(1+
            (Math.pow (Constant.Eulere, Math.random()*12-6)))) - CPipe.height));

    //Calmer
    int regrandom = (int)(Math.round(Math.random()*CPipe.pipeyrandom + CPipe.pipemin - CPipe.height));

    //Degree of y randomness value pipes spawn at
    //200 good
    static int pipeyrandom = 200;
}
