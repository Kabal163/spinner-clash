package com.mygdx.game.entity.item.weapon.lifecycle.action;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.entity.item.weapon.AbstractWeapon;
import com.mygdx.game.entity.item.weapon.Event;
import com.mygdx.game.entity.item.weapon.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

public class CreateSpriteAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractWeapon weapon = context.getStatefulObject();
        weapon.setSprite(new Sprite(weapon.getTexture()));
    }
}
