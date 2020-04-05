package com.mygdx.game.entity.item.weapon.lifecycle.action;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.entity.item.weapon.Event;
import com.mygdx.game.entity.item.weapon.State;
import com.mygdx.game.entity.item.weapon.AbstractWeapon;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

import static com.mygdx.game.Config.VIEW_HEIGHT;
import static com.mygdx.game.Config.VIEW_WIDTH;

public class SetStartPositionAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractWeapon weapon = context.getStatefulObject();
        Sprite sprite = weapon.getSprite();

        float posY = MathUtils.random(0, VIEW_HEIGHT - sprite.getHeight());
        sprite.setPosition(VIEW_WIDTH + 1, posY);
    }
}
