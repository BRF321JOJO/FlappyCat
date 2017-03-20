//This class simplifies drawing textures
package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Marla Scrub on 3/19/2017.
 */
public abstract class Image {

    public Texture texture;
    public SpriteBatch batch;
    public int width;
    public int height;
    public int posx;
    public int posy;
    public int velx;
    public int vely;
    public int ID;

    public Image(Texture texture, int posx, int posy, int width, int height, int velx, int vely, int ID, SpriteBatch batch){
        this.texture = texture;
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
        this.velx = velx;
        this.vely = vely;
        this.ID = ID;
        this.batch = batch;
    }

    public abstract void render();
}
