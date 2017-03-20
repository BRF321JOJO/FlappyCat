//This class generalizes the use of an entity in the game. This allows generalization of collision

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
    public Entity(Texture texture, int posx, int posy, int width, int height, int velx, int vely, int ID, SpriteBatch batch){
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

    //METHODS
    //This tests for collision
    public boolean isCollide(Entity e){
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
    //This generally says what to do when rendering and collision is happening
    //Varies depending on the subclass
    public abstract void render();
    public abstract void handleCollision(Entity e);
}