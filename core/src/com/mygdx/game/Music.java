package com.mygdx.game;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

/**
 * Created by Marla Scrub on 3/16/2017.
 */
public class Music {

    Sound MitchiriNekoMarch;

    public Music(){
        MitchiriNekoMarch = Gdx.audio.newSound(Gdx.files.internal("MitchiriNekoMarch.mp3"));
    }
    public void play(){
        MitchiriNekoMarch.loop(0.2f);
    }
}
