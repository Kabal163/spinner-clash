package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;
import static com.mygdx.game.Assets.BACKGROUND_NIGHT_CITY;

public abstract class AbstractScreen implements Screen, InputProcessor {

    protected final SpinnerGame game;

    protected Stage mainStage;
    protected Stage uiStage;

    private boolean paused;

    public AbstractScreen(SpinnerGame game) {
        this.game = game;

        mainStage = new Stage();
        uiStage = new Stage();
        paused = false;

        InputMultiplexer im = new InputMultiplexer(this);
        Gdx.input.setInputProcessor(im);

        create();
    }

    public abstract void create();

    public abstract void update(float delta);

    public abstract void renderScene(float delta);

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);

        uiStage.act(delta);
        mainStage.act(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.draw();
        uiStage.draw();
        renderScene(delta);
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean b) {
        paused = b;
    }

    public void togglePaused() {
        paused = !paused;
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
        uiStage.getViewport().update(width, height, true);
    }

    protected Actor createBackground() {
        Texture texture = new Texture(BACKGROUND_NIGHT_CITY);
        texture.setWrap(Repeat, Repeat);
        Actor background = new RepeatableActor(texture);
        background.setPosition(0, 0);

        return background;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
