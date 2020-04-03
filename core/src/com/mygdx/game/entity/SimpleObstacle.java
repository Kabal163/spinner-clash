package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.Assets.OBSTACLE_1;

/**
 * Not thread safe
 */
public class SimpleObstacle extends Obstacle {

    protected static TextureRegion texture;

    public SimpleObstacle() {
        if (texture == null) {
            texture = new TextureRegion(new Texture(OBSTACLE_1));
        }
        velocity = -500;
        score = 1;

        sprite = new Sprite(texture);
        setSize();
        setPosition();
    }
}
