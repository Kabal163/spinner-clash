package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameScreen;

import static com.mygdx.game.Assets.OBSTACLE_2;

/**
 * Not thread safe
 */
public class FastObstacle extends Obstacle {

    private static TextureRegion texture;

    public FastObstacle(GameScreen gameScreen) {
        super(gameScreen);

        if (texture == null) {
            texture = new TextureRegion(new Texture(OBSTACLE_2));
        }
        velocity = -700;
        score = 3;

        sprite = new Sprite(texture);
        setSize();
        setPosition();
    }

    @Override
    public void create() {

    }
}
