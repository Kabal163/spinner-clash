package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.entity.Explosion;
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
    private Explosion explosion;
    private Set<Obstacle> obstacles;
    private Label timeLabel;
    private Label gameOverLabel;

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

        gameOverLabel = createGameOverLabel();
        uiStage.addActor(gameOverLabel);
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

        for (Obstacle obstacle : obstacles) {
            if (obstacle.isCollided(player)) {
                failure = true;
            }
        }

        if (failure) {
            gameOverLabel.setVisible(true);
            if (explosion == null) {
                explosion = new Explosion(player.getX(), player.getY());
            }
            explosion.update(delta);
        }
    }

    @Override
    public void renderScene(float delta) {
        game.batch.begin();

        if (!failure) {
            player.draw(game.batch);
        } else if (!explosion.isEnded()) {
            explosion.draw(game.batch);
        }

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

    private Label createGameOverLabel() {
        BitmapFont font = new BitmapFont();
        String text = "GAME OVER";
        Label.LabelStyle style = new Label.LabelStyle(font, NAVY);
        Label gameOverLabel = new Label(text, style);
        gameOverLabel.setFontScale(4);
        gameOverLabel.setPosition(100, 240);
        gameOverLabel.setVisible(false);


        return gameOverLabel;
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
