package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameObject;
import com.mygdx.game.ObjectTag;

import static com.mygdx.game.Assets.SPINNER;
import static com.mygdx.game.Config.PLAYER_HEIGHT;
import static com.mygdx.game.Config.PLAYER_INIT_X_POS;
import static com.mygdx.game.Config.PLAYER_INIT_Y_POS;
import static com.mygdx.game.Config.PLAYER_ROTATION_DEGREE;
import static com.mygdx.game.Config.PLAYER_WIDTH;
import static com.mygdx.game.Config.VIEW_HEIGHT;
import static com.mygdx.game.ObjectTag.PLAYER;

public class Player implements GameObject {

    private float velocity;
    private final Sprite currentSprite;
    private final TextureRegion texture;

    public Player() {
        this.velocity = 0;
        this.texture = new TextureRegion(new Texture(SPINNER));
        this.currentSprite = new Sprite(texture);
        setSize();
        setOrigin();
        setPosition();
    }

    @Override
    public void update(float delta) {
        currentSprite.setY(currentSprite.getY() + velocity * delta);
        currentSprite.rotate(PLAYER_ROTATION_DEGREE * delta);

        if (currentSprite.getY() <= 0) {
            currentSprite.setY(0);
        }

        if (currentSprite.getY() >= VIEW_HEIGHT - currentSprite.getHeight()) {
            currentSprite.setY(VIEW_HEIGHT - currentSprite.getHeight());
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        currentSprite.draw(batch);
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        return currentSprite.getBoundingRectangle().overlaps(anotherObject.getCollider());
    }

    @Override
    public Rectangle getCollider() {
        return currentSprite.getBoundingRectangle();
    }

    @Override
    public ObjectTag getTag() {
        return PLAYER;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    private void setPosition() {
        currentSprite.setPosition(PLAYER_INIT_X_POS, PLAYER_INIT_Y_POS);
    }

    private void setSize() {
        currentSprite.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void setOrigin() {
        currentSprite.setOrigin(currentSprite.getWidth() / 2, currentSprite.getHeight() / 2);
    }
}
