package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entity.Background;
import com.mygdx.game.entity.Explosion;
import com.mygdx.game.entity.FastObstacle;
import com.mygdx.game.entity.GameOverLabel;
import com.mygdx.game.entity.Obstacle;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.ScoreLabel;
import com.mygdx.game.entity.SimpleObstacle;
import com.mygdx.game.entity.item.PickUpItem;
import com.mygdx.game.entity.item.weapon.Bullet;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.math.MathUtils.clamp;
import static com.mygdx.game.Config.OBSTACLE_CREATION_INTERVAL;
import static com.mygdx.game.Config.OBSTACLE_WIDTH;
import static com.mygdx.game.Config.WEAPON_CREATION_TIME_INTERVAL;

@Setter
@Getter
public class GameScreen extends AbstractScreen {

    private EntityCreationManager creationManager;

    private Background background;
    private Player player;
    private Explosion explosion;
    private List<Obstacle> obstacles;
    private List<PickUpItem> items;
    private List<Bullet> bullets;
    private ScoreLabel scoreLabel;
    private GameOverLabel gameOverLabel;
    private OverlapManager overlapManager;

    private float obstacleCreationTimeElapsed;
    private float weaponCreationTimeElapsed;
    private float lastAccelerationTimeElapsed;
    private float accelerationCount;
    private float fastObstacleCreationTimeElapsed;
    private boolean failure;
    private boolean pause;

    public GameScreen(SpinnerGame game) {
        super(game);

    }

    @Override
    public void create() {
        obstacleCreationTimeElapsed = 0;
        weaponCreationTimeElapsed = 0;
        lastAccelerationTimeElapsed = 0;
        accelerationCount = 0;
        fastObstacleCreationTimeElapsed = 0;

        creationManager = new GameScreenEntityCreationManager(this);

        creationManager.createBackground();
        creationManager.createPlayer();

        obstacles = new ArrayList<>();
        items = new ArrayList<>();
        bullets = new ArrayList<>();
        overlapManager = new OverlapManagerImpl(this);

        scoreLabel = new ScoreLabel(this);
        gameOverLabel = new GameOverLabel();
    }

    @Override
    public void update(float delta) {
        float localDelta = clamp(delta, delta, 1/30f);
        if (pause) {
            return;
        }
        overlapManager.update(localDelta);

        if (failure) {
            if (explosion == null) {
                explosion = new Explosion(player.getX(), player.getY());
            }
            explosion.update(localDelta);

            return;
        }

        obstacleCreationTimeElapsed += localDelta;
        weaponCreationTimeElapsed += localDelta;
        lastAccelerationTimeElapsed += localDelta;
        fastObstacleCreationTimeElapsed += localDelta;

        if (obstacleCreationTimeElapsed > OBSTACLE_CREATION_INTERVAL) {
            creationManager.createObstacle();
        }

        if (weaponCreationTimeElapsed > WEAPON_CREATION_TIME_INTERVAL) {
            weaponCreationTimeElapsed = 0;
            creationManager.createLaser();
        }

        obstacles.removeIf(obstacle -> obstacle.getX() < -OBSTACLE_WIDTH || obstacle.isExploded());
        bullets.removeIf(Bullet::isHit);

        player.update(localDelta);
        for (Obstacle obstacle : obstacles) {
            obstacle.update(localDelta);
        }

        for (PickUpItem item : items) {
            item.update(localDelta);
        }

        for (Bullet bullet : bullets) {
            bullet.update(localDelta);
        }

        scoreLabel.update(localDelta);
    }

    public void setFailure(boolean b) {
        this.failure = b;
    }

    @Override
    public void renderScene(float delta) {
        game.batch.begin();
        background.draw(game.batch);
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

        for (PickUpItem item : items) {
            item.draw(game.batch);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(game.batch);
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

        if (keycode == Keys.F) {
            player.useItem();
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
}
