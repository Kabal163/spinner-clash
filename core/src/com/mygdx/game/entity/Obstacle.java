package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.math.MathUtils.clamp;
import static com.mygdx.game.Config.OBSTACLE_HEIGHT;
import static com.mygdx.game.Config.OBSTACLE_WIDTH;
import static com.mygdx.game.Config.VIEW_HEIGHT;
import static com.mygdx.game.Config.VIEW_WIDTH;
import static com.mygdx.game.entity.ObjectTag.OBSTACLE;

public abstract class Obstacle implements GameObject {

    protected float velocity;
    protected Sprite sprite;
    protected boolean passed;
    protected int score;

    public Obstacle() {
        this.passed = false;
    }

    @Override
    public void update(float delta) {
        sprite.setX(sprite.getX() + velocity * clamp(delta, delta, 1/30f));
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        Rectangle collider = anotherObject.getCollider();

        return sprite.getX() < collider.getX() + collider.width / 2 &&
                sprite.getX() + sprite.getWidth() / 2 > collider.x &&
                sprite.getY() < collider.y + collider.height / 2 &&
                sprite.getY() + sprite.getHeight() / 2 > collider.y;
    }

    @Override
    public Rectangle getCollider() {
        return sprite.getBoundingRectangle();
    }

    @Override
    public ObjectTag getTag() {
        return OBSTACLE;
    }

    public void increaseVelocity(float velocity) {
        this.velocity += velocity;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
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

    protected void setPosition() {
        float posY = MathUtils.random(0, VIEW_HEIGHT - sprite.getHeight());
        sprite.setPosition(VIEW_WIDTH + 1, posY);
    }

    protected void setSize() {
        sprite.setSize(OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
    }
}
