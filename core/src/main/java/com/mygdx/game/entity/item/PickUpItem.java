package com.mygdx.game.entity.item;

import com.mygdx.game.entity.Expirable;
import com.mygdx.game.entity.GameObject;

public interface PickUpItem extends GameObject, Expirable {

    void use();

    void pickUp();

    void drop();

    boolean isPickedUp();
}
