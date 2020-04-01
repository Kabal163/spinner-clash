package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpinnerGame extends Game {

    public SpriteBatch batch;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        setScreen(new MenuScreen(this));
    }
}
