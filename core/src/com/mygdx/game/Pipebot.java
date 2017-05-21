package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 2/18/2017.
 */
public class Pipebot extends Entity{

    //Initializes value for a pipe

    public Pipebot(SpriteBatch batch, int posx) {
        super(
                new Texture ("pipebot.png"),
                posx,
//                (int)(Math.round(CPipe.pipemin + (300 - CPipe.pipemin)*(1/(1+
//                        (Math.pow (Math.E, Math.random()*12-6)))) - CPipe.height)),
                (int)Math.round(Math.random()*CPipe.pipeyrandom + CPipe.pipemin - CPipe.height),
                CPipe.width,
                CPipe.height,
                CPipe.pipevel,
                0,
                0,
                batch
        );
    }

    //Methods
    public void update(float delta) {

        //Moves pipe left across screen
        posx -= velx;

        if (posx<=CPipe.Lbound) {
            posx = CPipe.Rbound;

//            posy = (int)(Math.round(CPipe.pipemin + (300 - CPipe.pipemin)*
//                    (1/(1+ (Math.pow (Math.E, Math.random()*12-6)))) - CPipe.height));
            posy = (int)Math.round(Math.random()*CPipe.pipeyrandom + CPipe.pipemin - CPipe.height);
        }
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {
    }
}

