package com.mygdx.game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

/**
 * Created by Marla Scrub on 3/16/2017.
 */
public class Music {

    static boolean musiclooping;

    Sound MitchiriNekoMarch;

    public Music() {MitchiriNekoMarch = Gdx.audio.newSound(Gdx.files.internal("MitchiriNekoMarch.mp3"));}

    public void play() {MitchiriNekoMarch.loop(0.15f);}

    //public void pause() {MitchiriNekoMarch.loop();}


    public void update(float delta) {

//        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
//            musiclooping = false;
//        }
    }
}