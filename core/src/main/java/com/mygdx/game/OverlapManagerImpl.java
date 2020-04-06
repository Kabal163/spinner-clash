package com.mygdx.game;

import com.mygdx.game.entity.obstacle.AbstractObstacle;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.item.PickUpItem;
import com.mygdx.game.entity.item.bullet.Bullet;

import java.util.List;

import static com.mygdx.game.GameUtil.isCollided;

public class OverlapManagerImpl implements OverlapManager {

    private GameScreen gameScreen;

    public OverlapManagerImpl(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void update(float delta) {
        checkObstacleOverlapping();
        checkWeaponOverlapping();
        checkBulletOverlapping();
    }

    private void checkObstacleOverlapping() {
        Player player = gameScreen.getPlayer();
        List<AbstractObstacle> obstacles = gameScreen.getObstacles();

        for (AbstractObstacle obstacle : obstacles) {
            if (isCollided(obstacle, player)) {
                gameScreen.setFailure(true);
            }
        }
    }

    private void checkWeaponOverlapping() {
        Player player = gameScreen.getPlayer();
        List<PickUpItem> items = gameScreen.getItems();

        items.stream()
                .filter(i -> !i.isPickedUp())
                .filter(i -> isCollided(player, i))
                .findFirst()
                .ifPresent(player::setItem);
    }

    private void checkBulletOverlapping() {
        List<AbstractObstacle> obstacles = gameScreen.getObstacles();
        List<Bullet> bullets = gameScreen.getBullets();

        for (Bullet bullet : bullets) {
            for (AbstractObstacle obstacle : obstacles) {
                if (isCollided(obstacle, bullet)) {
                    bullet.hit();
                    obstacle.explode();
                }
            }
        }
    }
}
