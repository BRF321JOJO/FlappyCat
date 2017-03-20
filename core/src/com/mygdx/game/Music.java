package com.mygdx.game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

/**
 * Created by Marla Scrub on 3/16/2017.
 */
public class Music {

    static boolean musiclooping = true;

    Sound MitchiriNekoMarch;

    public Music() {
        MitchiriNekoMarch = Gdx.audio.newSound(Gdx.files.internal("MitchiriNekoMarch.mp3"));
    }

    public void play() {
        MitchiriNekoMarch.loop(0.2f);
    }


    public void update(float delta) {

        //Mute and Unmute

//        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
//            if (musiclooping) {
//                musiclooping = false;
//            }
//            if (!musiclooping) {
//                musiclooping = true;
//            }
//        }
//    }
    }
}
