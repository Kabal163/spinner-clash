package com.mygdx.game.entity.item.bullet.lifecycle.action;

import com.mygdx.game.entity.item.bullet.Bullet;
import com.mygdx.game.entity.item.bullet.Event;
import com.mygdx.game.entity.item.bullet.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

import static com.mygdx.game.entity.item.bullet.lifecycle.Constants.DELTA;

public class UpdatePositionAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        Bullet bullet = context.getStatefulObject();
        Float delta = context.getVariable(DELTA, Float.class);

        bullet.getSprite().setX(
                bullet.getSprite().getX() + bullet.getVelocity() * delta);
    }
}