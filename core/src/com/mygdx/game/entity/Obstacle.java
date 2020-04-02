package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameObject;
import com.mygdx.game.ObjectTag;

import static com.mygdx.game.Assets.OBSTACLE_1;
import static com.mygdx.game.Config.OBSTACLE_HEIGHT;
import static com.mygdx.game.Config.OBSTACLE_WIDTH;
import static com.mygdx.game.Config.VIEW_HEIGHT;
import static com.mygdx.game.Config.VIEW_WIDTH;
import static com.mygdx.game.ObjectTag.OBSTACLE;

public class Obstacle implements GameObject {

    private float velocity;
    private final Sprite currentSprite;
    private final TextureRegion texture;

    public Obstacle() {
        this.velocity = -500;
        this.texture = new TextureRegion(new Texture(OBSTACLE_1));
        this.currentSprite = new Sprite(texture);
        setSize();
        setPosition();
    }

    @Override
    public void update(float delta) {
        currentSprite.setX(currentSprite.getX() + velocity * delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        currentSprite.draw(batch);
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        currentSprite.setSize(currentSprite.getWidth() / 3, currentSprite.getHeight() / 3);
        boolean overlaps = currentSprite.getBoundingRectangle().overlaps(anotherObject.getCollider());
        setSize();

        return overlaps;
    }

    @Override
    public Rectangle getCollider() {
        return currentSprite.getBoundingRectangle();
    }

    @Override
    public ObjectTag getTag() {
        return OBSTACLE;
    }

    private void setPosition() {
        float posY = MathUtils.random(0, VIEW_HEIGHT - currentSprite.getHeight());
        currentSprite.setPosition(VIEW_WIDTH + 1, posY);
    }

    private void setSize() {
        currentSprite.setSize(OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
    }
}
