package com.mygdx.game.entity.obstacle.lifecycle.action;

import com.badlogic.gdx.math.Vector2;
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
        Float delta = context.getVariable(DELTA, Float.class);
        Vector2 pos = obstacle.getPosition();

        float x = pos.x + obstacle.getVelocity() * clamp(delta, delta, 1/30f);
        obstacle.setPosition(x, pos.y);
    }
}
