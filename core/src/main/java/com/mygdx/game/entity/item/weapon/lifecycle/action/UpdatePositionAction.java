package com.mygdx.game.entity.item.weapon.lifecycle.action;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.item.weapon.AbstractWeapon;
import com.mygdx.game.entity.item.weapon.Event;
import com.mygdx.game.entity.item.weapon.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

import static com.mygdx.game.entity.item.weapon.lifecycle.Constants.DELTA;

public class UpdatePositionAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractWeapon weapon = context.getStatefulObject();
        Float delta = context.getVariable(DELTA, Float.class);
        Vector2 pos = weapon.getPosition();

        weapon.setPosition(
                pos.x + weapon.getVelocity() * delta,
                pos.y);
    }
}
