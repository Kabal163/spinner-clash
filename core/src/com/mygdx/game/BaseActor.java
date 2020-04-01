package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseActor extends Actor {

    protected final TextureRegion region;
    protected final Rectangle boundary;
    protected float velocityX;
    protected float velocityY;

    public BaseActor(Texture texture) {
        super();
        region = new TextureRegion(texture);
        boundary = new Rectangle();
        velocityX = 0;
        velocityY = 0;
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
    }

    public Rectangle getBoundingRectangle() {
        boundary.set(getX(), getY(), getWidth(), getHeight());

        return boundary;
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        moveBy(velocityX * dt, velocityY * dt);
    }

    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            batch.draw(
                    region,
                    getX(),
                    getY(),
                    getOriginX(),
                    getOriginY(),
                    getWidth(),
                    getHeight(),
                    getScaleX(),
                    getScaleY(),
                    getRotation());
        }
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }
}
