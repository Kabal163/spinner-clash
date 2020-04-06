package com.mygdx.game.entity.obstacle.lifecycle.action;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.entity.obstacle.AbstractObstacle;
import com.mygdx.game.entity.obstacle.Event;
import com.mygdx.game.entity.obstacle.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

import static com.badlogic.gdx.math.MathUtils.clamp;
import static com.mygdx.game.entity.obstacle.lifecycle.Constants.DELTA;

public class UpdatePositionAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractObstacle obstacle = context.getStatefulObject();
        Sprite sprite = obstacle.getSprite();
        Float delta = context.getVariable(DELTA, Float.class);

        sprite.setX(sprite.getX() + obstacle.getVelocity() * clamp(delta, delta, 1/30f));
    }
}
