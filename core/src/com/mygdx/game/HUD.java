//All directly from Peter's code

package com.mygdx.game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.io.File;

/**
 * Created by peter on 3/25/17.
 */

public class HUD {
    Stage stage;
    Viewport viewport;
    Label score;
    Label highscore;


    public HUD(SpriteBatch batch) {
        viewport = new ScreenViewport(new OrthographicCamera());

        BitmapFont pixelFont = new BitmapFont(

                //File.separator acts as a / for distinguishing folders within folders
                Gdx.files.internal("font" + File.separator + "pixelOperatorHB.fnt"),
                false
        );

        stage = new Stage(viewport, batch);

        //Display Table (score)
        Table displayTable = new Table();
        //.top .center and such says where certain items should be displayed on screen
        displayTable.top();
        displayTable.setFillParent(true);


        //Test says what to display on screen
        //Color choices to display item
        score = new Label("Score: " + "scoreupdate", new Label.LabelStyle(pixelFont, Color.WHITE));
        highscore = new Label ("Highscore: " + "highscoreupdate", new Label.LabelStyle(pixelFont, Color.GOLD));

        //Size of text: (1-2 good)
        score.setFontScale(1.3F);
        highscore.setFontScale(1.1F);

        //expandX makes all displayed things equidistant in certain area rather side by side
        //Value after padTop is distance from top of screen score should be (10 good)
        displayTable.add(score).expandX().padTop(10);
        displayTable.add(highscore).expandX().padTop(10);

        stage.addActor(displayTable);
    }

    public void updateScore (String scoreupdate) {score.setText(scoreupdate);}
    public void updateHighscore (String highscoreupdate) {highscore.setText(highscoreupdate);}
}
