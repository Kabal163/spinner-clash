package com.mygdx.game.entity.item;

import com.mygdx.game.entity.Expirable;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Outsider;
import com.mygdx.game.entity.Size2D;

public interface PickUpItem extends GameObject, Expirable, Outsider, Size2D {

    void use();

    void pickUp();

    void drop();

    boolean isPickedUp();
}
