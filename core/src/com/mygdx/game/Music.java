package com.mygdx.game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

/**
 * Created by Marla Scrub on 3/16/2017.
 */
public class Music {

    //All the volumes for the sounds
    static float musicvolume  = 0.4f;
    static float SongofStormsmusicvolume = 0.4f;
    //(0.1 - 0.3 good)
    static float flappypointvolume = 0.3f;
    static float catvolume = 0.2f;
    static float laservolume = 1.0f;
    static float deadvolume = 1.0f;
    static float wingflapvolume = 0.7f;
    static float IMQAZIvolume = 1.0f;

    //Defines which song is played
    String backgroundsong = "MitchiriNekoMarch.mp3";
    String SongofStormsbackgroundsong = "Song of Storms.mp3";

    //Helps to define if music is playing
    static boolean musicmuted = false;

    //May break game, made into static as apposed to non-static
    static Sound backgroundmusic;
    static Sound SongofStormsbackgroundmusic;

    public Music() {
        //List of songs defined here
        backgroundmusic = Gdx.audio.newSound(Gdx.files.internal(backgroundsong));
        SongofStormsbackgroundmusic = Gdx.audio.newSound(Gdx.files.internal(SongofStormsbackgroundsong));
    }

    //Defines general idea of backgroundmusic
    static public void startmusic() {backgroundmusic.loop(musicvolume);}
    static public void pausemusic() {backgroundmusic.pause();}
    static public void resumemusic() {backgroundmusic.resume();}

    //Defines Song of Storms
    static public void startSongofStormsmusic() {SongofStormsbackgroundmusic.loop(SongofStormsmusicvolume);}
    static public void pauseSongofStormsmusic() {SongofStormsbackgroundmusic.pause();}
    static public void resumeSongofStormsmusic() {SongofStormsbackgroundmusic.resume();}


    //Defines Song of Storms being played once(Starts if never played, otherwise resumes)
    public static void playSongofStorms () {
        if (!Whynot.SongofStormsplayedonce) {
            Music.startSongofStormsmusic();
            Whynot.SongofStormsplayedonce = true;
        } else {
            Music.resumeSongofStormsmusic();
        }
    }

    public void update(float delta) {

        //Mute function
        if (Gdx.input.isKeyJustPressed(Input.Keys.M) && !musicmuted) {
            pausemusic();
            pauseSongofStormsmusic();
            musicmuted = true;

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.M) && musicmuted) {
            if (!Whynot.InBound) {
                resumemusic();
            }
            if (Whynot.InBound) {
                playSongofStorms();
            }

            musicmuted = false;
        }
    }
}