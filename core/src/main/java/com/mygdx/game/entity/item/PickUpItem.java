package com.mygdx.game.entity.item;

import com.mygdx.game.entity.GameObject;

public interface PickUpItem extends GameObject {

    void pickUp();

    boolean isPickedUp();

    boolean isOver();

    void drop();
}
