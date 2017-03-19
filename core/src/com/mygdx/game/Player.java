//This class is an example of how to use the Entity class

package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity {

    //Functions
    public Player(Texture texture, int posx, int posy, int width, int height, int velx, int vely, int ID, SpriteBatch batch) {
        super(texture, posx, posy, width, height, velx, vely, ID, batch);
    }

    //Methods
    @Override
    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }

    @Override
    public void handleCollision(Entity e) {
       //Result of collision, for example:
        // EndGame = true;
    }
}