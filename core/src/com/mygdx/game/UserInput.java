package com.mygdx.game;
import java.util.Scanner;

/**
 * Created by Marla Scrub on 3/12/2017.
 */
public class UserInput {

    Scanner UserInput = new Scanner(System.in);

    //User input class
    //Can write welcome words
    //Can teach how to play and controls
    //Can hint at secret

    public void update(float delta) {

        System.out.println("Type x coordinate");
        int x = UserInput.nextInt();
        System.out.println("Type y coordinate");
        int y = UserInput.nextInt();
        System.out.println("Type z coordinate");
        int z = UserInput.nextInt();

        System.out.println(
                x / (Math.sqrt(x * x + y * y + z * z)) + "," +
                        y / (Math.sqrt(x * x + y * y + z * z)) + "," +
                        z / (Math.sqrt(x * x + y * y + z * z))

                );
    }
}
