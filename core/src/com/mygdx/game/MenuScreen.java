package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import static com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import static com.mygdx.game.Assets.TITLE;

public class MenuScreen extends AbstractScreen {

    public MenuScreen(SpinnerGame game) {
        super(game);
    }

    @Override
    public void create() {
        uiStage.addActor(createBackground());
        uiStage.addActor(createTitle());
        uiStage.addActor(createInstructions());
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void renderScene(float delta) {

    }

    private Actor createTitle() {
        Texture texture = new Texture(TITLE);
        Actor title = new BaseActor(texture);
        title.setPosition(20, 250);

        return title;
    }

    private Actor createInstructions() {
        BitmapFont font = new BitmapFont();
        String text = " Press S to start ";
        LabelStyle style = new LabelStyle(font, Color.WHITE);
        Label instructions = new Label(text, style);
        instructions.setFontScale(2);
        instructions.setPosition(200, 230);

        return instructions;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.S) {
            game.setScreen(new GameScreen(game));
        }

        return false;
    }
}
