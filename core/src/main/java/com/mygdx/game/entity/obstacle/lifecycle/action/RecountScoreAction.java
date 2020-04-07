package com.mygdx.game.entity.obstacle.lifecycle.action;

import com.badlogic.gdx.Screen;
import com.mygdx.game.GameScreen;
import com.mygdx.game.entity.ScoreLabel;
import com.mygdx.game.entity.obstacle.AbstractObstacle;
import com.mygdx.game.entity.obstacle.Event;
import com.mygdx.game.entity.obstacle.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

public class RecountScoreAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractObstacle obstacle = context.getStatefulObject();
        Screen screen = obstacle.getGameContext().getScreen();

        if (screen instanceof GameScreen) {
            GameScreen gameScreen = (GameScreen) screen;
            ScoreLabel scoreLabel = gameScreen.getScoreLabel();
            scoreLabel.increaseScore(obstacle.getScore());
        }
    }
}
