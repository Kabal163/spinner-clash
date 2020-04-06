package com.mygdx.game;

import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.obstacle.AbstractObstacle;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.item.PickUpItem;
import com.mygdx.game.entity.item.bullet.Bullet;

public interface EntityCreationManager {

    GameObject createBackground();

    Player createPlayer();

    AbstractObstacle createObstacle();

    PickUpItem createLaser();

    Bullet createBullet();
}
