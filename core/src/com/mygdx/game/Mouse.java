//package com.mygdx.game;
//import com.badlogic.gdx.Gdx;
//
///**
// * Created by Marla Scrub on 4/2/2017.
// */
//public class Mouse {
//
//    //Area of click ability
//    int leftx = 0;
//    int rightx = Constant.screenwidth;
//    int bottomy = 0;
//    int topy = Constant.screenheight;
//
//    int counter = 0;
//
//    //Tests if mouse is clicked once in given x and y coordinates
//    //There is another method which allows continuous holding of button.
//
//    public void update (float delta) {
//        if (Gdx.input.justTouched()) {
//
//            //Defines the x and y coordinates which mouse can be clicked
//            if (Gdx.input.getX() > leftx && Gdx.input.getX() < rightx) {
//                if(Gdx.input.getY() > bottomy && Gdx.input.getY() < topy){
//
//                    counter++;
//
//                    if (counter <= 20) {
//                        System.out.println("Ow");
//                    } else if (20 < counter && counter <= 50) {
//                        System.out.println("Stop poking me!");
//                    } else if (50 < counter && counter <= 90) {
//                        System.out.println("*Pokes back*");
//                    } else if (90 < counter && counter < 200) {
//                        System.out.println("Poked to death");
//                    } else if (counter >= 200) {
//                        System.out.println("Don't you have something better to do?");
//                    }
//                }
//            }
//        }
//    }
//}
