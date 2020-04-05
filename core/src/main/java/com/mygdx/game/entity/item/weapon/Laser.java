package com.mygdx.game.entity.item.weapon;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameScreen;
import com.mygdx.game.GameUtil;
import com.mygdx.game.SpinnerGame;
import com.mygdx.game.entity.GameObject;
import lombok.Getter;
import lombok.Setter;

import static com.mygdx.game.Assets.LASER;
import static com.mygdx.game.entity.item.weapon.Event.CREATE;
import static com.mygdx.game.entity.item.weapon.Event.DROP;
import static com.mygdx.game.entity.item.weapon.Event.PICK_UP;
import static com.mygdx.game.entity.item.weapon.State.EXPIRED;
import static com.mygdx.game.entity.item.weapon.State.PICKED_UP;

public class Laser extends AbstractWeapon {

    private static TextureRegion texture;

    public Laser(SpinnerGame gameContext) {
        super(gameContext);

        if (texture == null) {
            texture = new TextureRegion(new Texture(LASER));
        }
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public void use() {
        if (!PICKED_UP.equals(state)) {
            return;
        }
        Screen screen = gameContext.getScreen();

        if (screen instanceof GameScreen) {
            GameScreen gameScreen = (GameScreen) screen;
            gameScreen.getCreationManager().createBullet();
        }
    }
}
