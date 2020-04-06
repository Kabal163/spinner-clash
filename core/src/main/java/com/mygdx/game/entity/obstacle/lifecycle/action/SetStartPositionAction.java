package com.mygdx.game.entity.obstacle.lifecycle.action;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.entity.obstacle.AbstractObstacle;
import com.mygdx.game.entity.obstacle.Event;
import com.mygdx.game.entity.obstacle.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

import static com.mygdx.game.Config.VIEW_HEIGHT;
import static com.mygdx.game.Config.VIEW_WIDTH;

public class SetStartPositionAction implements Action<State, Event> {

    private static final float MIN_Y_POS = 0;
    private static final float START_X_POS = VIEW_WIDTH + 1;

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractObstacle obstacle = context.getStatefulObject();
        Sprite sprite = obstacle.getSprite();

        float startYPos = MathUtils.random(MIN_Y_POS, VIEW_HEIGHT - sprite.getHeight());
        sprite.setPosition(START_X_POS, startYPos);
    }
}