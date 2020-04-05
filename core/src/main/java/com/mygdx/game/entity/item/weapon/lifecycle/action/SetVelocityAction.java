package com.mygdx.game.entity.item.weapon.lifecycle.action;

import com.mygdx.game.entity.item.weapon.AbstractWeapon;
import com.mygdx.game.entity.item.weapon.Event;
import com.mygdx.game.entity.item.weapon.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

import static com.mygdx.game.Config.WEAPON_VELOCITY;

public class SetVelocityAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractWeapon weapon = context.getStatefulObject();
        weapon.setVelocity(WEAPON_VELOCITY);
    }
}
