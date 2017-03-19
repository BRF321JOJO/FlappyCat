package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Input;

public class GameScreen implements Screen {

    //FIELDS
    private MyGdxGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private final int LEVEL_WIDTH;
    private final int LEVEL_HEIGHT;

    //Initialized Objects: Order of spawning in
    Background background;
    Music music;
    Qazi qazi;
    Pipebot[] pipebot;
    Pipetop[] pipetop;
    Constant constant;
    Laser laser;
    Score score;
    Whynot whynot;

    //CONSTRUCTOR
    public GameScreen(MyGdxGame game) {
        this.game = game;

        LEVEL_WIDTH = MyGdxGame.V_WIDTH;
        LEVEL_HEIGHT = MyGdxGame.V_HEIGHT;
        gameCam = new OrthographicCamera();
        gamePort = new ExtendViewport(LEVEL_WIDTH, LEVEL_HEIGHT, gameCam);

        //All following: Makes one or multiple new objects

        background = new Background();
        music = new Music();
        //Plays looping music
        music.play();
        qazi = new Qazi(game.batch);

        pipebot = new Pipebot[CPipe.numberofpipes];
        for (int i = 0; i <= CPipe.numpipesmax; i++) {
            // Places pipes equal distances apart starting from RBound to further right. Note: Disregards pipe width
            pipebot[i] = new Pipebot(game.batch,
                    (i * CPipe.pipespace) + CPipe.Rbound);

            //Adds pipebot to entity ArrayList
            Entity.entities.add(pipebot[i]);
        }

        pipetop = new Pipetop[CPipe.numberofpipes];
        for (int i = 0; i <= CPipe.numpipesmax; i++) {
            //Pipebot remain constant so can use Pipebot values
            pipetop[i] = new Pipetop(game.batch,
                    (i * CPipe.pipespace) + CPipe.Rbound,
                    pipebot[i].posy + CPipe.pipeabove);

            //Adds pipetop to enetiy ArrayList
            Entity.entities.add(pipetop[i]);
        }
        constant = new Constant();
        laser = new Laser();
        score = new Score();
        whynot = new Whynot();
    }

    //METHODS
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);

        //Clears Screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Allows images to be transparent
        game.batch.enableBlending();

        game.batch.begin();

        //Must be first as in back
        game.batch.draw(background.texture, background.posx, background.posy, background.width, background.height);
        //Twice to extend background and allow loop. Value is added to the posx to allow be further.
        game.batch.draw(background.texture, background.posx + background.width, background.posy, background.width, background.height);

        //Draws Qazi
        qazi.render();

        //Draws position of each 8 up pipes using array/for loop
        for (int i = 0; i <= CPipe.numpipesmax; i++) {
            pipebot[i].render();
        }

        //Draws position for each 8 down pipes using array/for loop
        for (int i = 0; i <= CPipe.numpipesmax; i++) {
            pipetop[i].render();
        }

        game.batch.draw(laser.texture, laser.posx, laser.posy, laser.width, laser.height);
        game.batch.draw(score.texture, score.posx, score.posy, score.width, score.height);
        game.batch.draw(whynot.texture, whynot.posx, whynot.posy, whynot.width, whynot.height);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void dispose() {
    }


    public void update(float delta) {

        //Says to only update if the EndGame isn't true
        if (!Constant.EndGame) {

            background.update(delta);

            //Updates player position
            qazi.update(delta);

            //Updates each up pipe position
            for (int i = 0; i <= CPipe.numpipesmax; i++) {
                pipebot[i].update(delta);
            }

            //Updates game each down pipe position
            for (int i = 0; i <= CPipe.numpipesmax; i++) {

                //Takes method from update (delta) and enacts it
                pipetop[i].update(delta);

                //Moves pipetop certain distance above pipebot
                pipetop[i].posy = pipebot[i].posy + CPipe.pipeabove;
            }

            laser.update(delta);

            //Shoots laser based from Qazi posy (only if off screen)
            if (Gdx.input.isKeyJustPressed(Input.Keys.L) && !Laser.InBound) {
                laser.posy = qazi.posy;
            }

            score.update(delta);

            //Increases score when Qazi passes pipe
            for (int i = 0; i <= CPipe.numpipesmax; i++) {
                if (pipebot[i].posx == (qazi.posx - CPipe.width)) {
                    Score.scorevalue++;
                    System.out.println("Score: " + Score.scorevalue);
                }
            }
        }

        //Note: The following functions stay out of the if(!constant.EndGame) condition
        constant.update(delta);
        whynot.update(delta);

        //Checks for collision. Comes from Entity class. Collision happens after everything updates
        //Note: Only checks collision for Qazi
        for (Entity e : Entity.entities) {
            if (qazi.isCollide(e)) {
                qazi.handleCollision(e);
                e.handleCollision(qazi);
            }
        }

        //Code no longer applies due to collision. Keep if collision is removed.

//        //Continues game once space is pressed (but only if death from hitting ceiling or floor)
//        if (!Qazi.InBound && (Gdx.input.isKeyJustPressed(Input.Keys.T))) {
//            qazi.posy = Qazi.startposy;
//            qazi.vely = Qazi.velyconstant;
//            Qazi.InBound = true;
//            Constant.EndGame = false;
//            System.out.println("Game continued from where left off");
//        }

        if (Constant.EndGame && (Gdx.input.isKeyJustPressed(Input.Keys.T))) {
            Qazi.InBound = true;
            Constant.EndGame = false;
            System.out.println("Game restarted");

            //The following resets positions of all objects
            qazi.posy = Qazi.startposy;
            qazi.vely = Qazi.velyconstant;
            for (int i = 0; i <= CPipe.numpipesmax; i++) {
                pipebot[i].posy = (i * CPipe.pipespace) + CPipe.Rbound;
            }
            laser.posx = Constant.Holdingarea;

            //Resets score
            Score.scorevalue = 0;
        }
    }
}
