package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Assets;

import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;
import static com.mygdx.game.Config.BACKGROUND_X_POS;
import static com.mygdx.game.Config.BACKGROUND_Y_POS;
import static com.mygdx.game.entity.ObjectTag.BACKGROUND;

public class Background implements GameObject {

    private static TextureRegion texture;

    private final Sprite sprite;
    private int sourceX;

    public Background() {
        if (texture == null) {
            Texture t = new Texture(Assets.BACKGROUND);
            t.setWrap(Repeat, Repeat);
            texture = new TextureRegion(t);
        }

        sprite = new Sprite(texture);
        sprite.setPosition(BACKGROUND_X_POS, BACKGROUND_Y_POS);
        sourceX = 0;
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        sourceX += 5;
        texture.setRegionHeight(Gdx.graphics.getHeight());
        batch.draw(
                texture.getTexture(),
                BACKGROUND_X_POS,
                BACKGROUND_Y_POS,
                sourceX,
                0,
                texture.getRegionWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        return false;
    }

    @Override
    public Rectangle getCollider() {
        return null;
    }

    @Override
    public ObjectTag getTag() {
        return BACKGROUND;
    }
}
