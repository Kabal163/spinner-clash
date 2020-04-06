package com.mygdx.game.entity.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameScreen;
import com.mygdx.game.SpinnerGame;

import static com.mygdx.game.Assets.OBSTACLE_1;
import static com.mygdx.game.Config.SIMPLE_OBSTACLE_SCORE;
import static com.mygdx.game.Config.SIMPLE_OBSTACLE_VELOCITY;

/**
 * Not thread safe
 */
public class SimpleObstacle extends AbstractObstacle {

    protected static TextureRegion texture;

    public SimpleObstacle(SpinnerGame gameContext) {
        super(gameContext);

        if (texture == null) {
            texture = new TextureRegion(new Texture(OBSTACLE_1));
        }
        velocity = SIMPLE_OBSTACLE_VELOCITY;
    }

    @Override
    public int getScore() {
        return SIMPLE_OBSTACLE_SCORE;
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }
}
