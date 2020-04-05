package com.mygdx.game.entity.item.weapon.lifecycle.action;

import com.badlogic.gdx.Screen;
import com.mygdx.game.GameScreen;
import com.mygdx.game.entity.item.weapon.AbstractWeapon;
import com.mygdx.game.entity.item.weapon.Event;
import com.mygdx.game.entity.item.weapon.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

public class AddWeaponToGameScreenAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractWeapon weapon = context.getStatefulObject();
        Screen screen = weapon.getGameContext().getScreen();

        if (screen instanceof GameScreen) {
            GameScreen gameScreen = (GameScreen) screen;
            gameScreen.getItems().add(weapon);
        } else {
            throw new IllegalStateException();
        }
    }
}
