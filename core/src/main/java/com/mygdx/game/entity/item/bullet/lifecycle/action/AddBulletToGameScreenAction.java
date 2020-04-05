package com.mygdx.game.entity.item.bullet.lifecycle.action;

import com.badlogic.gdx.Screen;
import com.mygdx.game.GameScreen;
import com.mygdx.game.entity.item.bullet.Bullet;
import com.mygdx.game.entity.item.bullet.Event;
import com.mygdx.game.entity.item.bullet.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

public class AddBulletToGameScreenAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        Bullet bullet = context.getStatefulObject();
        Screen screen = bullet.getGameContext().getScreen();

        if (screen instanceof GameScreen) {
            GameScreen gameScreen = (GameScreen) screen;
            gameScreen.getBullets().add(bullet);
        } else {
            throw new IllegalStateException();
        }
    }
}
