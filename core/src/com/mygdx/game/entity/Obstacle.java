package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameObject;
import com.mygdx.game.ObjectTag;

import static com.badlogic.gdx.math.MathUtils.clamp;
import static com.mygdx.game.Assets.OBSTACLE_1;
import static com.mygdx.game.Config.OBSTACLE_HEIGHT;
import static com.mygdx.game.Config.OBSTACLE_WIDTH;
import static com.mygdx.game.Config.VIEW_HEIGHT;
import static com.mygdx.game.Config.VIEW_WIDTH;
import static com.mygdx.game.ObjectTag.OBSTACLE;

public class Obstacle implements GameObject {

    private float velocity;
    private final Sprite currentSprite;
    private static TextureRegion texture;
    private boolean passed;
    private int score;

    public Obstacle() {
        if (texture == null) {
            texture = new TextureRegion(new Texture(OBSTACLE_1));
        }
        this.passed = false;
        this.velocity = -500;

        this.currentSprite = new Sprite(texture);
        score = 1;
        setSize();
        setPosition();
    }

    @Override
    public void update(float delta) {
        currentSprite.setX(currentSprite.getX() + velocity * clamp(delta, delta, 1/30f));
    }

    @Override
    public void draw(SpriteBatch batch) {
        currentSprite.draw(batch);
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        Rectangle collider = anotherObject.getCollider();

        return currentSprite.getX() < collider.getX() + collider.width / 2 &&
                currentSprite.getX() + currentSprite.getWidth() / 2 > collider.x &&
                currentSprite.getY() < collider.y + collider.height / 2 &&
                currentSprite.getY() + currentSprite.getHeight() / 2 > collider.y;
    }

    @Override
    public Rectangle getCollider() {
        return currentSprite.getBoundingRectangle();
    }

    @Override
    public ObjectTag getTag() {
        return OBSTACLE;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public void increaseVelocity(float velocity) {
        this.velocity += velocity;
    }

    public float getX() {
        return currentSprite.getX();
    }

    public float getY() {
        return currentSprite.getY();
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int getScore() {
        return score;
    }

    private void setPosition() {
        float posY = MathUtils.random(0, VIEW_HEIGHT - currentSprite.getHeight());
        currentSprite.setPosition(VIEW_WIDTH + 1, posY);
    }

    private void setSize() {
        currentSprite.setSize(OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
    }
}
