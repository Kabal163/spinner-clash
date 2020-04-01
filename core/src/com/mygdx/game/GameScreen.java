package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.entity.Obstacle;
import com.mygdx.game.entity.Player;

import java.util.HashSet;
import java.util.Set;

import static com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.graphics.Color.NAVY;
import static com.mygdx.game.Config.OBSTACLE_CREATION_INTERVAL;

public class GameScreen extends AbstractScreen {

    private Actor background;
    private Player player;
    private Set<Obstacle> obstacles;
    private Label timeLabel;

    private float timeElapsed;
    private boolean failure;

    public GameScreen(SpinnerGame game) {
        super(game);
    }

    @Override
    public void create() {
        timeElapsed = 0;
        background = createBackground();
        mainStage.addActor(background);

        player = new Player();
        obstacles = new HashSet<>();

        timeLabel = createTimeLabel();
        uiStage.addActor(timeLabel);
    }

    @Override
    public void update(float delta) {
        timeElapsed += delta;
        timeLabel.setText("Time: " + (int)timeElapsed);

        if (timeElapsed > OBSTACLE_CREATION_INTERVAL) {
            timeElapsed = 0;
            obstacles.add(new Obstacle());
        }
        player.update(delta);
        for (Obstacle obstacle : obstacles) {
            obstacle.update(delta);
        }
    }

    @Override
    public void renderScene(float delta) {
        game.batch.begin();
        player.draw(game.batch);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(game.batch);
        }
        game.batch.end();
    }

    private Label createTimeLabel() {
        BitmapFont font = new BitmapFont();
        String text = "Time: 0";
        Label.LabelStyle style = new Label.LabelStyle(font, NAVY);
        Label timeLabel = new Label(text, style);
        timeLabel.setFontScale(2);
        timeLabel.setPosition(500, 440);

        return timeLabel;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.M) {
            game.setScreen(new MenuScreen(game));
        }

        if (keycode == Keys.DOWN) {
            player.setVelocity(-250);
        }

        if (keycode == Keys.UP) {
            player.setVelocity(250);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.DOWN) {
            player.setVelocity(0);

            if (Gdx.input.isKeyPressed(Keys.UP)) {
                player.setVelocity(250);
            }
        }

        if (keycode == Keys.UP) {
            player.setVelocity(0);

            if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                player.setVelocity(-250);
            }
        }

        return false;
    }
}
