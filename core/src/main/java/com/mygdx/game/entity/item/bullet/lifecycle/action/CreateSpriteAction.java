package com.mygdx.game.entity.item.bullet.lifecycle.action;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.entity.item.bullet.Bullet;
import com.mygdx.game.entity.item.bullet.Event;
import com.mygdx.game.entity.item.bullet.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

public class CreateSpriteAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        Bullet bullet = context.getStatefulObject();
        bullet.setSprite(new Sprite(Bullet.texture));
    }
}
