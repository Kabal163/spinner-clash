package com.mygdx.game.entity.item.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameScreen;
import com.mygdx.game.GameUtil;
import com.mygdx.game.entity.GameObject;

import static com.mygdx.game.Assets.LASER;
import static com.mygdx.game.Config.LASER_LIFE_LENGTH;
import static com.mygdx.game.Config.VIEW_HEIGHT;
import static com.mygdx.game.Config.VIEW_WIDTH;

public class Laser extends Weapon {

    private static TextureRegion texture;

    private float timeElapsed;
    private boolean pickedUp;
    private Sprite sprite;
    private float velocity;

    public Laser(GameScreen screen) {
        super(screen);

        if (texture == null) {
            texture = new TextureRegion(new Texture(LASER));
        }

        sprite = new Sprite(texture);
        sprite.setSize(150, 150);
        velocity = -500;
        setPosition();
    }

    @Override
    public void update(float delta) {
        timeElapsed += delta;

        if (!pickedUp) {
            sprite.setX(sprite.getX() + velocity * delta);
            return;
        }

        if (gameScreen.getPlayer().isItemUsed()) {
            gameScreen.getCreationManager().createBullet();
            gameScreen.getPlayer().markAsUsed();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (!pickedUp) {
            sprite.draw(batch);
        }
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
    public void drop() {
        pickedUp = false;
    }

    @Override
    public boolean isOver() {
        return timeElapsed >= LASER_LIFE_LENGTH;
    }

    private void setPosition() {
        float posY = MathUtils.random(0, VIEW_HEIGHT - sprite.getHeight());
        sprite.setPosition(VIEW_WIDTH + 1, posY);
    }
}
