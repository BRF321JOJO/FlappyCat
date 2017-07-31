package com.mygdx.game;
import com.badlogic.gdx.Gdx;

/**
 * Created by Marla Scrub on 4/2/2017.
 */
public class Mouse {

    //Area of click ability
    int leftx = 0;
    int rightx = Constant.screenwidth;
    int bottomy = 0;
    int topy = Constant.screenheight;

    public void update (float delta) {

        //Tests if mouse is clicked once in given x and y coordinates
        //There is another method which allows continuous holding of button.
        if (Gdx.input.justTouched()) {

            //Defines the x and y coordinates which mouse can be clicked
            if (Gdx.input.getX() > leftx && Gdx.input.getX() < rightx) {
                if(Gdx.input.getY() > bottomy && Gdx.input.getY() < topy){

                    CPipe.pipespace += 200;
                    System.out.println("200 more distance");


                }
            }
        }
    }
}
