package com.mygdx.game;

import com.mygdx.game.entity.Background;
import com.mygdx.game.entity.FastObstacle;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Obstacle;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.SimpleObstacle;
import com.mygdx.game.entity.item.PickUpItem;
import com.mygdx.game.entity.item.bullet.Bullet;
import com.mygdx.game.entity.item.weapon.Laser;

public class GameScreenEntityCreationManager implements EntityCreationManager {

    private final GameScreen gameScreen;

    public GameScreenEntityCreationManager(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public GameObject createBackground() {
        Background background = new Background();
        gameScreen.setBackground(background);

        return background;
    }

    @Override
    public Player createPlayer() {
        Player player = new Player(gameScreen.game);
        gameScreen.setPlayer(player);

        return player;
    }

    @Override
    public Obstacle createObstacle() {
        gameScreen.setObstacleCreationTimeElapsed(0);
        Obstacle obstacle;
        if ((int) gameScreen.getFastObstacleCreationTimeElapsed() > 7) {
            obstacle = new FastObstacle(gameScreen);
            gameScreen.setFastObstacleCreationTimeElapsed(0);
        } else {
            obstacle = new SimpleObstacle(gameScreen);
        }

        if (gameScreen.getLastAccelerationTimeElapsed() > 5) {
            gameScreen.setLastAccelerationTimeElapsed(0);
            gameScreen.setAccelerationCount(gameScreen.getAccelerationCount() + 1);
        }
        obstacle.increaseVelocity(-10 * gameScreen.getAccelerationCount());
        gameScreen.getObstacles().add(obstacle);

        return obstacle;
    }

    @Override
    public PickUpItem createLaser() {
        Laser laser = new Laser(gameScreen.game);
        laser.create();

        return laser;
    }

    @Override
    public Bullet createBullet() {
        Bullet bullet = new Bullet(gameScreen.game);
        bullet.create();

        return bullet;
    }
}
