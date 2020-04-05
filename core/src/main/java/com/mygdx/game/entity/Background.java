package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;
import static com.mygdx.game.Assets.BACKGROUND_NIGHT_CITY;
import static com.mygdx.game.Config.BACKGROUND_X_POS;
import static com.mygdx.game.Config.BACKGROUND_Y_POS;
import static com.mygdx.game.entity.ObjectTag.BACKGROUND;

public class Background implements GameObject {

    private static TextureRegion texture;

    private final Sprite sprite;
    private int sourceX;

    public Background() {
        if (texture == null) {
            Texture t = new Texture(BACKGROUND_NIGHT_CITY);
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
        batch.draw(
                texture.getTexture(),
                BACKGROUND_X_POS,
                BACKGROUND_Y_POS,
                sourceX,
                0,
                (int)sprite.getWidth(),
                (int)sprite.getHeight());
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

    @Override
    public boolean isOutOfGame() {
        return false;
    }
}
