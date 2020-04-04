package com.mygdx.game.entity.item.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameScreen;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.ObjectTag;
import com.mygdx.game.entity.item.PickUpItem;

import static com.mygdx.game.Config.VIEW_HEIGHT;
import static com.mygdx.game.Config.VIEW_WIDTH;

public abstract class Weapon implements PickUpItem {

    protected final GameScreen gameScreen;

    public Weapon(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        return false;
    }

    @Override
    public Rectangle getCollider() {
        return null;
    }

    @Override
    public ObjectTag getTag() {
        return null;
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public void pickUp() {

    }

    @Override
    public boolean isPickedUp() {
        return false;
    }

    @Override
    public void drop() {

    }
}
