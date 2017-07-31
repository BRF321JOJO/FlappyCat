package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 2/18/2017.
 */
public class Player extends Entity{

    Sound Wingflap = Gdx.audio.newSound(Gdx.files.internal("Wingflap.mp3"));
    Sound Dead = Gdx.audio.newSound(Gdx.files.internal("Dead.mp3"));

    public Player(SpriteBatch batch) {
        super(
                new Texture("Salsacat.png"),
                100,
                CPlayer.startposy,
                CPlayer.playerwidth,
                CPlayer.playerheight,
                0,
                //Initial velocity
                CPlayer.velyconstant,
                0,
                batch
        );
    }

//Methods

    public void update(float delta) {

        //Changes y position based on vely value
        posy += vely;

        //Moves image up
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

            vely = CPlayer.velyconstant;

            //Plays sound of flap
            Wingflap.play(Music.wingflapvolume);
        }

        //Moves image constantly down
        else if (posy > 0) {
            vely--;
        }

        // Larger moves ceiling down, less moves up
        //*Note: Differing heights are fine. Does not require change of experimentaltop value.
        //For height: 516 (489)
        //For height: 600. (400)
        int experimentaltop = 489;
        int ceiling = experimentaltop + height;



        //Defines InBound

        if (posy <= 0 || posy >= (MyGdxGame.V_HEIGHT - ceiling)) {
            CPlayer.InBound = false;
        } else {
            CPlayer.InBound = true;}

        //Stops image movement at ceiling and floor
        if (!CPlayer.InBound) {
            vely = 0;
            Constant.EndGame = true;
            //Makes it so only make sound once after death and not repeated
            if (!CPlayer.dead) {
                Dead.play(Music.deadvolume);
                CPlayer.dead = true;
            }
        }
    }

    @Override
    public void render() {batch.draw(texture, posx, posy, width, height);}
    @Override
    public void handleCollision(Entity e) {
        Constant.EndGame = true;
        //Makes it so only do action once after hit pipe
        if (!CPlayer.dead) {
            Dead.play(Music.deadvolume);
            CPlayer.dead = true;
        }
        CPlayer.InBound = false;
    }
}
