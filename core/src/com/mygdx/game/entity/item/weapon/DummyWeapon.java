package com.mygdx.game.entity.item.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameScreen;
import com.mygdx.game.GameUtil;
import com.mygdx.game.entity.GameObject;

import static com.mygdx.game.Assets.LASER;

public class DummyWeapon extends Weapon {

    private boolean pickedUp;
    private static TextureRegion texture;
    private Sprite sprite;

    public DummyWeapon(GameScreen screen) {
        super(screen);

        if (texture == null) {
            texture = new TextureRegion(new Texture(LASER));
        }

        sprite = new Sprite(texture);
    }

    @Override
    public void update(float delta) {
        return;
    }

    @Override
    public void draw(SpriteBatch batch) {
        return;
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        return GameUtil.isCollided(this, anotherObject);
    }

    @Override
    public Rectangle getCollider() {
        return sprite.getBoundingRectangle();
    }

    @Override
    public void pickUp() {
        pickedUp = true;
    }

    @Override
    public boolean isPickedUp() {
        return pickedUp;
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public void drop() {
        pickedUp = false;
    }
}
