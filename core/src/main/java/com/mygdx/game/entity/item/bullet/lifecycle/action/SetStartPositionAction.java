package com.mygdx.game.entity.item.bullet.lifecycle.action;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameScreen;
import com.mygdx.game.entity.item.bullet.Bullet;
import com.mygdx.game.entity.item.bullet.BulletEvent;
import com.mygdx.game.entity.item.bullet.BulletState;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

public class SetStartPositionAction implements Action<BulletState, BulletEvent> {

    @Override
    public void execute(StateContext<BulletState, BulletEvent> context) {
        Bullet bullet = context.getStatefulObject();
        Screen screen = bullet.gameContext.getScreen();
        GameScreen gameScreen;

        if (screen instanceof GameScreen) {
            gameScreen = (GameScreen) screen;
        } else {
            throw new IllegalStateException();
        }

        Rectangle player = gameScreen.getPlayer().getCollider();

        bullet.getSprite().setX(player.getX() + player.getWidth() / 2);
        bullet.getSprite().setY(player.getY() + player.getHeight() / 3);
    }
}