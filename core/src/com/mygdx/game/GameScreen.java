package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.entity.Explosion;
import com.mygdx.game.entity.GameOverLabel;
import com.mygdx.game.entity.Obstacle;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.ScoreLabel;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.badlogic.gdx.Input.Keys;
import static com.mygdx.game.Config.OBSTACLE_CREATION_INTERVAL;

public class GameScreen extends AbstractScreen {

    private Actor background;
    private Player player;
    private Explosion explosion;
    private LinkedList<Obstacle> obstacles;
    private ScoreLabel scoreLabel;
    private GameOverLabel gameOverLabel;

    private float obstacleCreationTimeElapsed;
    private float lastAccelerationTimeElapsed;
    private float accelerationCount;
    private boolean failure;
    private boolean pause;

    private float timeElapse;
    private int fps;

    public GameScreen(SpinnerGame game) {
        super(game);
    }

    @Override
    public void create() {
        timeElapse = 0;
        fps = 0;
        obstacleCreationTimeElapsed = 0;
        lastAccelerationTimeElapsed = 0;
        accelerationCount = 0;
        background = createBackground();
        mainStage.addActor(background);

        player = new Player();
        obstacles = new LinkedList<>();

        scoreLabel = new ScoreLabel(this);
        gameOverLabel = new GameOverLabel();
    }

    @Override
    public void update(float delta) {
        if (pause) {
            return;
        }

        for (Obstacle obstacle : obstacles) {
            if (obstacle.isCollided(player)) {
                failure = true;
            }
        }

        if (failure) {
            if (explosion == null) {
                explosion = new Explosion(player.getX(), player.getY());
            }
            explosion.update(delta);

            return;
        }

        obstacleCreationTimeElapsed += delta;
        lastAccelerationTimeElapsed += delta;
        timeElapse += delta;

        if (obstacleCreationTimeElapsed > OBSTACLE_CREATION_INTERVAL) {
            obstacleCreationTimeElapsed = 0;
            Obstacle obstacle = new Obstacle();

            if (lastAccelerationTimeElapsed > 5) {
                lastAccelerationTimeElapsed = 0;
                accelerationCount++;
            }
            obstacle.increaseVelocity(-10 * accelerationCount);
            obstacles.add(obstacle);
        }
        player.update(delta);
        for (Obstacle obstacle : obstacles) {
            obstacle.update(delta);
        }
        scoreLabel.update(delta);
    }

    @Override
    public void renderScene(float delta) {
        game.batch.begin();
        scoreLabel.draw(game.batch);

        if (failure) {
            if (!explosion.isEnded()) {
                explosion.draw(game.batch);
            }
            gameOverLabel.draw(game.batch);
            game.batch.end();
            return;
        }

        player.draw(game.batch);

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(game.batch);
        }

        game.batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.M) {
            game.setScreen(new MenuScreen(game));
        }

        if (keycode == Keys.P) {
            togglePause();
        }

        if (keycode == Keys.DOWN) {
            player.setVelocity(-250 + -accelerationCount * 5);
        }

        if (keycode == Keys.UP) {
            player.setVelocity(250 + accelerationCount * 5);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.DOWN) {
            player.setVelocity(0);

            if (Gdx.input.isKeyPressed(Keys.UP)) {
                player.setVelocity(250 + accelerationCount * 5);
            }
        }

        if (keycode == Keys.UP) {
            player.setVelocity(0);

            if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                player.setVelocity(-250 + -accelerationCount * 5);
            }
        }

        return false;
    }

    private void togglePause() {
        pause = !pause;
    }

    public LinkedList<Obstacle> getObstacles() {
        return obstacles;
    }

    public Player getPlayer() {
        return player;
    }
}
