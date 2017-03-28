package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 2/19/2017.
 */
public class Pipetop extends Entity{

    public Pipetop (SpriteBatch batch, int posx, int posy) {
        super (
                new Texture ("pipetop.png"),
                posx,
                posy,
                CPipe.width,
                CPipe.height,
                CPipe.pipevel,
                0,
                0,
                batch
        );
    }

    public void update(float delta) {
        posx -= velx;

        if (posx<= CPipe.Lbound) {
            posx = CPipe.Rbound;

            //Posy of pipetop is determined in Gamescreen update
        }
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {Constant.EndGame = true;}
}