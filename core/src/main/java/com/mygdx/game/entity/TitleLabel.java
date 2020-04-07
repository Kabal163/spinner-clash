package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static com.mygdx.game.Assets.SPINNER_CLASH_TITLE;
import static com.mygdx.game.Config.SPINNER_CLASH_TITLE_X_POS;
import static com.mygdx.game.Config.SPINNER_CLASH_TITLE_Y_POS;
import static com.mygdx.game.entity.ObjectTag.LABEL;

public class TitleLabel implements GameObject {

    private static TextureRegion texture;

    private Sprite sprite;

    public TitleLabel() {
        if (texture == null) {
            texture = new TextureRegion(new Texture(SPINNER_CLASH_TITLE));
        }

        sprite = new Sprite(texture);
        sprite.setPosition(SPINNER_CLASH_TITLE_X_POS, SPINNER_CLASH_TITLE_Y_POS);
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
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
        return LABEL;
    }
}
