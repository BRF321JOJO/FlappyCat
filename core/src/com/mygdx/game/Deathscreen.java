package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 3/19/2017.
 */
public class Deathscreen extends Image{

    static boolean onscreen;

    public Deathscreen(SpriteBatch batch) {
        super (
                new Texture("Deathscreen.png"),
                Constant.Holdingarea,
                350,
                500,
                50,
                0,
                0,
                0,
                batch
        );
    }

    public void update(float delta) {

        //Defines onscreen
        if (posx >=0 && posx <=CPipe.Rbound) {
            onscreen = true;
        } else {onscreen = false;}


        //Defines when image should be on-screen
        if (CQazi.dead) {
            posx = 200;
        }

        //Moves image back
        if (Gdx.input.isKeyJustPressed(Input.Keys.T) && Constant.EndGame && onscreen) {
            posx = Constant.Holdingarea;
        }
    }

    @Override
    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }
}