package com.mygdx.game.entity.obstacle.lifecycle.action;

import com.mygdx.game.entity.obstacle.AbstractObstacle;
import com.mygdx.game.entity.obstacle.Event;
import com.mygdx.game.entity.obstacle.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

import static com.mygdx.game.entity.obstacle.lifecycle.Constants.DELTA;

public class UpdateStateTimeAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractObstacle obstacle = context.getStatefulObject();
        obstacle.setStateTime(obstacle.getStateTime() + context.getVariable(DELTA, Float.class));
    }
}
