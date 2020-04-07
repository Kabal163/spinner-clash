package com.mygdx.game.entity.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.SpinnerGame;

import static com.mygdx.game.Assets.EXPLOSION;
import static com.mygdx.game.Assets.OBSTACLE_2;
import static com.mygdx.game.Config.FAST_OBSTACLE_SCORE;
import static com.mygdx.game.Config.FAST_OBSTACLE_VELOCITY;

/**
 * Not thread safe
 */
public class FastObstacle extends AbstractObstacle {

    private static TextureRegion obstacleTexture = new TextureRegion(new Texture(OBSTACLE_2));
    private static TextureRegion explosionTexture = new TextureRegion(new Texture(EXPLOSION));

    public FastObstacle(SpinnerGame gameContext) {
        super(gameContext);
        velocity = FAST_OBSTACLE_VELOCITY;
    }

    @Override
    public int getScore() {
        return FAST_OBSTACLE_SCORE;
    }

    @Override
    public TextureRegion getTexture() {
        return obstacleTexture;
    }
}
