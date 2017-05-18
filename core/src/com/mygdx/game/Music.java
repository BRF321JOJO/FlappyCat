package com.mygdx.game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

/**
 * Created by Marla Scrub on 3/16/2017.
 */
public class Music {

    //All the volumes for the sounds

    //Song volumes
    private static float Mitchirivolume  = 0.4f;
    private static float Ecstasyvolume = 0.4f;
    private static float INeedaHerovolume = 0.4f;

    //Event volumes
    //(0.1 - 0.3 good)
    public static float flappypointvolume = 0.3f;
    public static float catvolume = 0.2f;
    public static float laservolume = 1.0f;
    public static float deadvolume = 1.0f;
    public static float wingflapvolume = 0.7f;
    public static float IMQAZIvolume = 1.0f;

    //Defines which song is played
    String Mitchirisong = "MitchiriNekoMarch.mp3";
    String Ecstasysong = "Ecstasy of Gold Accordion Cover.mp3";
    String INeedaHerosong = "I Need a Hero.mp3";

    //Helps to define if music is playing
    static boolean musicmuted = false;

    private static Sound Mitchirimusic;
    private static Sound Ecstasymusic;
    private static Sound INeedaHeromusic;

    public Music() {
        //List of songs defined here
        Mitchirimusic = Gdx.audio.newSound(Gdx.files.internal(Mitchirisong));
        Ecstasymusic = Gdx.audio.newSound(Gdx.files.internal(Ecstasysong));
        INeedaHeromusic = Gdx.audio.newSound(Gdx.files.internal(INeedaHerosong));
    }

    //Defines general idea of backgroundmusic
    static public void startMitchiri() {Mitchirimusic.loop(Mitchirivolume);}
    static public void pauseMitchiri() {Mitchirimusic.pause();}
    static public void resumeMitchiri() {Mitchirimusic.resume();}

    //Defines Song of Storms
    static public void startEcstasy() {Ecstasymusic.loop(Ecstasyvolume);}
    static public void pauseEcstasy() {Ecstasymusic.pause();}
    static public void resumeEcstasy() {Ecstasymusic.resume();}

    static public void startINeedaHero() {INeedaHeromusic.loop(INeedaHerovolume);}
    static public void pauseINeedaHero() {INeedaHeromusic.pause();}
    static public void resumeINeedaHero() {INeedaHeromusic.resume();}


    //Defines songs being played once (Starts if never played, otherwise resumes)
    public static void playEcstasy () {
        if (!Whynot.Ecstacyplayedonce) {
            Music.startEcstasy();
            Whynot.Ecstacyplayedonce = true;
        } else {Music.resumeEcstasy();}
    }

    public static void playINeedaHero () {
        if (!Whynot.Heroplayedonce) {
            Music.startINeedaHero();
            Whynot.Heroplayedonce = true;
        } else {Music.resumeINeedaHero();}
    }


    public void update(float delta) {

        //Mute function

        if (Gdx.input.isKeyJustPressed(Input.Keys.M) && !musicmuted) {
            //Mutes all songs by pausing them, regardless if they have ben played before
            pauseMitchiri();
            pauseEcstasy();
            pauseINeedaHero();
            musicmuted = true;

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.M) && musicmuted) {
            //Only resumes Mitchiri if Whynot image not on screen to prevent Mitchiri playing when Whynot songs...
            //...meant to be playing
            if (!Whynot.InBound) {
                resumeMitchiri();
            }
            //Only plays Ecstacy (the most likely to play) if unmuted while Whynot image is on screen to prevent...
            //...allowing the player to unmute and get the secret song.
            if (Whynot.InBound) {
                playEcstasy();
            }
            musicmuted = false;
        }
    }
}