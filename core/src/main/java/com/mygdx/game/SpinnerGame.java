package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.SneakyThrows;

public class SpinnerGame extends Game {

    public SpriteBatch batch;
    public LifecycleManagerFactory lifecycleManagerFactory;

    @Override
    @SneakyThrows
    public void create() {
        this.batch = new SpriteBatch();
        setScreen(new MenuScreen(this));
        lifecycleManagerFactory = new LifecycleManagerFactoryImpl();
    }
}
