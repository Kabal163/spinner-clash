package com.mygdx.game.entity.item;

import com.mygdx.game.entity.GameObject;

public interface PickUpItem extends GameObject {

    void use();

    void pickUp();

    void drop();

    boolean isPickedUp();

    boolean isExpired();
}
