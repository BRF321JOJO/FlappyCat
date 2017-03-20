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
    Titlescreen titlescreen;
    Deathscreen deathscreen;
    Whynot whynot;

    //CONSTRUCTOR
    public GameScreen(MyGdxGame game) {
        this.game = game;

        LEVEL_WIDTH = MyGdxGame.V_WIDTH;
        LEVEL_HEIGHT = MyGdxGame.V_HEIGHT;
        gameCam = new OrthographicCamera();
        gamePort = new ExtendViewport(LEVEL_WIDTH, LEVEL_HEIGHT, gameCam);

        //All following: Makes one or multiple new objects

        background = new Background(game.batch);
        music = new Music();
        //Plays looping music (depends on mute function)
        if (Music.musiclooping && Debug.musicallowed) {
            music.play();
        }
        qazi = new Qazi(game.batch);

        //Kept these pipe for statements separate to make readable

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
        laser = new Laser(game.batch);
        score = new Score();
        titlescreen = new Titlescreen(game.batch);
        deathscreen = new Deathscreen(game.batch);
        whynot = new Whynot(game.batch);
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
        background.render();
        //Twice to extend background and allow loop. Value is added to the posx to allow be further.
        game.batch.draw(background.texture, background.posx + background.width, background.posy, background.width, background.height);

        //Draws Qazi
        qazi.render();

        //Draws position of each 8 up and down pipes using array/for loop
        for (int i = 0; i <= CPipe.numpipesmax; i++) {
            pipebot[i].render();
            pipetop[i].render();
        }

        laser.render();
        game.batch.draw(score.texture, score.posx, score.posy, score.width, score.height);
        titlescreen.render();
        deathscreen.render();
        whynot.render();

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

            //Updates each pipe positions
            for (int i = 0; i <= CPipe.numpipesmax; i++) {
                pipebot[i].update(delta);

                //Takes method from update (delta) and enacts it
                pipetop[i].update(delta);

                //Moves pipetop certain distance above pipebot
                pipetop[i].posy = pipebot[i].posy + CPipe.pipeabove;
            }

            laser.update(delta);

            //Shoots laser based from player posy (only if off screen)
            if (Gdx.input.isKeyJustPressed(Input.Keys.L) && !Laser.InBound) {
                laser.posy = qazi.posy;
            }

            score.update(delta);

            //Increases score when player passes pipe
            for (int i = 0; i <= CPipe.numpipesmax; i++) {
                if (pipebot[i].posx == (qazi.posx - CPipe.width)) {
                    Score.scorevalue++;
                    System.out.println("Score: " + Score.scorevalue);
                }
            }
        }

        //Note: The following functions stay out of the if(!constant.EndGame) condition
        music.update(delta);
        constant.update(delta);
        titlescreen.update(delta);
        deathscreen.update(delta);
        whynot.update(delta);


        //Following:
        // Methods for GameScreen

        //Checks for collision. Comes from Entity class. Collision happens after everything updates
        //Note: Only checks collision for player
        if (Debug.playerCollide) {
            for (Entity e : Entity.entities) {
                if (qazi.isCollide(e)) {
                    //Tests for collision
                    //Put any specifics or sounds in their respective classes, not here
                    qazi.handleCollision(e);
                    e.handleCollision(qazi);
                }
            }
        }

        //Code only applies if Collision off.
        //Continues game once space is pressed (but only if death from hitting ceiling or floor)
        if (!Debug.playerCollide) {
            if (!CQazi.InBound && (Gdx.input.isKeyJustPressed(Input.Keys.T))) {
                qazi.posy = CQazi.startposy;
                qazi.vely = CQazi.velyconstant;
                CQazi.InBound = true;
                Constant.EndGame = false;
                System.out.println("Game continued from where left off");
            }
        }

        //Restarts game entirely

        if (Constant.EndGame && (Gdx.input.isKeyJustPressed(Input.Keys.T))) {
            if (!Titlescreen.onscreen) {
                //Reset values
                CQazi.InBound = true;
                Constant.EndGame = false;
                CQazi.dead = false;
                System.out.println("Game restarted");

                //The following resets positions of all objects
                qazi.posy = CQazi.startposy;
                qazi.vely = CQazi.velyconstant;
                for (int i = 0; i <= CPipe.numpipesmax; i++) {
                    pipebot[i].posx = (i * CPipe.pipespace) + CPipe.Rbound;
                    pipetop[i].posx = (i * CPipe.pipespace) + CPipe.Rbound;

                    //Resets y pos. Otherwise would just move back without new randomness
                    pipebot[i].posy = (int) (Math.round(CPipe.pipemin + (300 - CPipe.pipemin) * (1 / (1 + (Math.pow(Constant.Eulere, Math.random() * 12 - 6)))) - CPipe.height));
                }

                //Found another possibility
                //If set pipebot[i].posy to high/low value works also because moves pipes off screen in y position,
                //*but causes first instance the pipes to be far away

                laser.posx = Constant.Holdingarea;

                //Resets score
                Score.scorevalue = 0;
            }
        }
    }
}
