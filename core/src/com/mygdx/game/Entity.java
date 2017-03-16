package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by peter on 2/19/17.
 */

public abstract class Entity {

    //FIELDS
    public Texture texture;
    public SpriteBatch batch;  //Draw Textures
    public int width;
    public int height;
    public int posx;
    public int posy;
    public int velx;
    public int vely;
    public int ID;             //Unique identity

    //STATIC FIELDS
    public static ArrayList<Entity> entities = new ArrayList<Entity>();

    //CONSTRUCTOR
    public Entity(int width, int height, int posx, int posy, int velx, int vely, int ID, Texture texture, SpriteBatch batch){
        this.width = width;
        this.height = height;
        this.posx = posx;
        this.posy = posy;
        this.velx = velx;
        this.vely = vely;
        this.ID = ID;
        this.batch = batch;
        this.texture = texture;
    }

    //METHODS
    public boolean Collision(Entity e){
        if(     posx < e.posx + e.width     &&
                posx + width > e.posx       &&
                posy < e.posy + e.height    &&
                height + posy > e.posy)
            {
                return true;
            } else {
                return false;
        }
    }

    //ABSTRACT METHODS
    //Varies depending on the subclass*
    public abstract void render();

}