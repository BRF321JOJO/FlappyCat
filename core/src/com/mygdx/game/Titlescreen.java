package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 3/19/2017.
 */
public class Titlescreen extends Image{

    static boolean onscreen;

    public Titlescreen(SpriteBatch batch) {
        super (
                new Texture("Titlescreen.png"),
                200,
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

        //Stops game if image is on-screen
        if (onscreen) {
            Constant.EndGame = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && Constant.EndGame && onscreen) {
            posx = Constant.Holdingarea;
            Constant.EndGame = false;
        }
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
}
