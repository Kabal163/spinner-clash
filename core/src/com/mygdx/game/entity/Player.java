package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static com.mygdx.game.Assets.SPINNER;
import static com.mygdx.game.Config.PLAYER_HEIGHT;
import static com.mygdx.game.Config.PLAYER_INIT_X_POS;
import static com.mygdx.game.Config.PLAYER_INIT_Y_POS;
import static com.mygdx.game.Config.PLAYER_ROTATION_DEGREE;
import static com.mygdx.game.Config.PLAYER_WIDTH;
import static com.mygdx.game.Config.VIEW_HEIGHT;
import static com.mygdx.game.entity.ObjectTag.PLAYER;

public class Player implements GameObject {

    private float velocity;
    private final Sprite sprite;
    private final TextureRegion texture;

    public Player() {
        this.velocity = 0;
        this.texture = new TextureRegion(new Texture(SPINNER));
        this.sprite = new Sprite(texture);
        setSize();
        setOrigin();
        setPosition();
    }

    @Override
    public void update(float delta) {
        sprite.setY(sprite.getY() + velocity * delta);
        sprite.rotate(PLAYER_ROTATION_DEGREE * delta);

        if (sprite.getY() <= 0) {
            sprite.setY(0);
        }

        if (sprite.getY() >= VIEW_HEIGHT - sprite.getHeight()) {
            sprite.setY(VIEW_HEIGHT - sprite.getHeight());
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        return sprite.getBoundingRectangle().overlaps(anotherObject.getCollider());
    }

    @Override
    public Rectangle getCollider() {
        return sprite.getBoundingRectangle();
    }

    @Override
    public ObjectTag getTag() {
        return PLAYER;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public void increaseVelocity(float velocity) {
        this.velocity += velocity;
    }

    private void setPosition() {
        sprite.setPosition(PLAYER_INIT_X_POS, PLAYER_INIT_Y_POS);
    }

    private void setSize() {
        sprite.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void setOrigin() {
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    }
}
