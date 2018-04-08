package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
    Player player;
    Pipebot[] pipebot;
    Pipetop[] pipetop;
    Constant constant;
    Laser laser;
    Score score;
    HUD hud;
    Titlescreen titlescreen;
    Deathscreen deathscreen;
    Whynot whynot;

    //Separate because does not actually contribute to gameplay
    Mouse mouse;

    //For score update sound
    Sound Flappypoint = Gdx.audio.newSound(Gdx.files.internal("Flappypoint.mp3"));

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

        //Plays looping music (depends on debug mute)
        if (Debug.musicallowed) {
            music.startMitchiri();
        }

        player = new Player(game.batch);

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
        hud = new HUD(game.batch);
        titlescreen = new Titlescreen(game.batch);
        deathscreen = new Deathscreen(game.batch);
        whynot = new Whynot(game.batch);
        mouse = new Mouse();
    }

    //METHODS
    @Override
    public void show() {}

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

        //Draws Player
        player.render();

        //Draws position of each 8 up and down pipes using array/for loop
        for (int i = 0; i <= CPipe.numpipesmax; i++) {
            pipebot[i].render();
            pipetop[i].render();
        }

        laser.render();

        //Draws HUD
        //Must end and being hud so can draw properly in order.
        game.batch.end();
        hud.stage.draw();
        game.batch.begin();

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


    //Methods

    //Restarts game entirely

    public void restartgame () {
        //The following reset all values
        CPlayer.InBound = true;
        Constant.EndGame = false;
        CPlayer.dead = false;
        System.out.println("Game restarted");

        //The following resets positions of all objects
        player.posy = CPlayer.startposy;
        player.vely = CPlayer.velyconstant;

        for (int i = 0; i <= CPipe.numpipesmax; i++) {
            pipebot[i].posx = (i * CPipe.pipespace) + CPipe.Rbound;
            pipetop[i].posx = (i * CPipe.pipespace) + CPipe.Rbound;

            //Resets y pos. (Otherwise would just move back without new randomness)
//            pipebot[i].posy = (int) (Math.round(CPipe.pipemin + (300 - CPipe.pipemin) * (1 / (1 +
//                             (Math.pow(Math.E, Math.random() * 12 - 6)))) - CPipe.height));
            pipebot[i].posy = (int) Math.round(Math.random() * CPipe.pipeyrandom + CPipe.pipemin - CPipe.height);
        }
        laser.posx = Constant.Holdingarea;
        whynot.posx = Constant.Holdingarea;

        //Resets scores and highscore message
        Score.scorevalue = 0;
        Score.newhighscore = false;

        //Prints you died message
        Deathscreen.Printonce = true;

        //The following resets the music
        //Resets game by making Mitchiri start and all other music stop (only is music is not muted anyway)
        if (!Music.musicmuted) {
            Music.pauseEcstasy();
            Music.pauseINeedaHero();
            Music.resumeMitchiri();
            Whynot.Heroplaying = false;
        }
    }

    //End of code which restarts game


    //Updates game using update method in each class
    public void update(float delta) {

        //Says to only update if the EndGame isn't true
        if (!Constant.EndGame) {

            background.update(delta);

            //Updates player position
            player.update(delta);

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
                laser.posy = player.posy;
            }

            score.update(delta);

            //Increases score when player passes pipe
            for (int i = 0; i <= CPipe.numpipesmax; i++) {
                if (pipebot[i].posx == (player.posx - CPipe.width)) {
                    Score.scorevalue++;
                    System.out.println("Score: " + Score.scorevalue);
                    Flappypoint.play(Music.flappypointvolume);
                }
            }

            //Updates score on screen
            //Needs to be where is now [after score.update and it's increase above]
            hud.updateScore("Score: " + Score.scorevalue);
            hud.updateHighscore("Highscore: " + Score.highscorevalue);
        }

        //Note: The following functions stay out of the if(!constant.EndGame) condition (but within update)


        music.update(delta);
        constant.update(delta);
        titlescreen.update(delta);
        deathscreen.update(delta);
        whynot.update(delta);
        mouse.update(delta);


        //Following:
        // Methods for GameScreen (in update)
        //These methods work even if Endgame is false (as stated above)

        //Following resets highscore
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.T) && CPlayer.dead
                && Score.highscorevalue>0) {
            Score.highscorevalue = 0;
            System.out.println("Score Reset: Due to Holding Down L-Shift While Restarting");
        }

        //Restarts game
        if (Constant.EndGame && (Gdx.input.isKeyJustPressed(Input.Keys.T))) {
            //Cannot restart on title screen
            if (!Titlescreen.onscreen) {
                restartgame();
            }
        }

        //Checks for collision. Comes from Entity class. Collision happens after everything updates.
        //Note: Only checks collision for things defined, not all entities
        //Only if collision is allowed in debug
        if (Debug.playerCollide) {
            //Generally tests if an entity collides with anything on the list of entities
            for (Entity e : Entity.entities) {
                //Checks collision for player specifically
                if (player.isCollide(e)) {
                    //Says all handling denoted within respective class
                    player.handleCollision(e);
                    e.handleCollision(player);
                }
                //Checks collision for laser
                if (laser.isCollide(e)) {
                    laser.handleCollision(e);
                    e.handleCollision(laser);
                }
            }
        }

        //*Code only applies if Collision off*
        //Continues game once space is pressed (but only if death from hitting ceiling or floor)
        if (!Debug.playerCollide) {
            if (!CPlayer.InBound && (Gdx.input.isKeyJustPressed(Input.Keys.T))) {
                player.posy = CPlayer.startposy;
                player.vely = CPlayer.velyconstant;
                CPlayer.InBound = true;
                Constant.EndGame = false;
                System.out.println("Game continued");
            }
        }
        //End of update method
    }
}
