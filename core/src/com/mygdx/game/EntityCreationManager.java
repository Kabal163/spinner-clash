package com.mygdx.game;

import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Obstacle;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.item.PickUpItem;
import com.mygdx.game.entity.item.weapon.Bullet;

public interface EntityCreationManager {

    GameObject createBackground();

    Player createPlayer();

    Obstacle createObstacle();

    PickUpItem createLaser();

    Bullet createBullet();
}
