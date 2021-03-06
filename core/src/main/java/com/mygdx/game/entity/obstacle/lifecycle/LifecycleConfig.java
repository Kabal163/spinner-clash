package com.mygdx.game.entity.obstacle.lifecycle;

import com.mygdx.game.entity.obstacle.Event;
import com.mygdx.game.entity.obstacle.State;
import com.mygdx.game.entity.obstacle.lifecycle.action.RecountScoreAction;
import com.mygdx.game.entity.obstacle.lifecycle.action.ResetStateTimeAction;
import com.mygdx.game.entity.obstacle.lifecycle.action.SetSizeAction;
import com.mygdx.game.entity.obstacle.lifecycle.action.SetStartPositionAction;
import com.mygdx.game.entity.obstacle.lifecycle.action.CreateExplosionAction;
import com.mygdx.game.entity.obstacle.lifecycle.action.UpdatePositionAction;
import com.mygdx.game.entity.obstacle.lifecycle.action.UpdateStateTimeAction;
import com.mygdx.game.lifecycle.TransitionConfigurer;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.LifecycleConfiguration;

import static com.mygdx.game.entity.obstacle.Event.CREATE;
import static com.mygdx.game.entity.obstacle.Event.EXPLODE;
import static com.mygdx.game.entity.obstacle.Event.FLY_AWAY;
import static com.mygdx.game.entity.obstacle.Event.PASS;
import static com.mygdx.game.entity.obstacle.Event.UPDATE;
import static com.mygdx.game.entity.obstacle.State.EXPLODED;
import static com.mygdx.game.entity.obstacle.State.INIT;
import static com.mygdx.game.entity.obstacle.State.MOVING;
import static com.mygdx.game.entity.obstacle.State.OUTSIDER;
import static com.mygdx.game.entity.obstacle.State.PASSED;

public class LifecycleConfig implements LifecycleConfiguration<State, Event> {

    private final Action<State, Event> setSizeAction;
    private final Action<State, Event> setStartPositionAction;
    private final Action<State, Event> updatePositionAction;
    private final Action<State, Event> resetStateTime;
    private final Action<State, Event> recountScoreAction;
    private final Action<State, Event> updateStateTime;
    private final Action<State, Event> createExplosionAction;

    public LifecycleConfig() {
        setSizeAction = new SetSizeAction();
        setStartPositionAction = new SetStartPositionAction();
        updatePositionAction = new UpdatePositionAction();
        resetStateTime = new ResetStateTimeAction();
        recountScoreAction = new RecountScoreAction();
        updateStateTime = new UpdateStateTimeAction();
        createExplosionAction = new CreateExplosionAction();
    }

    @Override
    public void configureTransitions(TransitionConfigurer<State, Event> configurer) {
        configurer
                .with()
                .sourceState(INIT)
                .targetState(MOVING)
                .event(CREATE)
                .action(setSizeAction)
                .action(setStartPositionAction)

                .with()
                .sourceState(MOVING)
                .targetState(PASSED)
                .event(PASS)
                .action(resetStateTime)
                .action(recountScoreAction)

                .with()
                .sourceState(MOVING)
                .targetState(EXPLODED)
                .event(EXPLODE)
                .action(resetStateTime)
                .action(recountScoreAction)
                .action(createExplosionAction)

                .with()
                .sourceState(MOVING)
                .targetState(OUTSIDER)
                .event(FLY_AWAY)
                .action(resetStateTime)

                .with()
                .sourceState(MOVING)
                .targetState(MOVING)
                .event(UPDATE)
                .action(updatePositionAction)
                .action(updateStateTime)

                .with()
                .sourceState(PASSED)
                .targetState(PASSED)
                .event(UPDATE)
                .action(updatePositionAction)
                .action(updateStateTime)

                .with()
                .sourceState(PASSED)
                .targetState(OUTSIDER)
                .event(FLY_AWAY)

                // All bullets will be removed from the screen on the next game loop iteration
                .with()
                .sourceState(OUTSIDER)
                .targetState(OUTSIDER)
                .event(UPDATE)

                .with()
                .sourceState(EXPLODED)
                .targetState(EXPLODED)
                .event(UPDATE);
    }
}
