package com.mygdx.game.entity.obstacle.lifecycle.action;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.entity.obstacle.AbstractObstacle;
import com.mygdx.game.entity.obstacle.Event;
import com.mygdx.game.entity.obstacle.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

import static com.mygdx.game.Config.OBSTACLE_HEIGHT;
import static com.mygdx.game.Config.OBSTACLE_WIDTH;

public class SetSizeAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractObstacle obstacle = context.getStatefulObject();
        Sprite sprite = obstacle.getSprite();
        sprite.setSize(OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
    }
}
