package com.mygdx.game.entity.item.weapon.lifecycle;

import com.mygdx.game.entity.item.weapon.Event;
import com.mygdx.game.entity.item.weapon.State;
import com.mygdx.game.entity.item.weapon.lifecycle.action.SetSizeAction;
import com.mygdx.game.entity.item.weapon.lifecycle.action.SetStartPositionAction;
import com.mygdx.game.entity.item.weapon.lifecycle.action.UpdatePositionAction;
import com.mygdx.game.entity.item.weapon.lifecycle.action.UpdateTimeElapsedAction;
import com.mygdx.game.lifecycle.TransitionConfigurer;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.LifecycleConfiguration;

import static com.mygdx.game.entity.item.weapon.Event.CREATE;
import static com.mygdx.game.entity.item.weapon.Event.DROP;
import static com.mygdx.game.entity.item.weapon.Event.EXPIRE;
import static com.mygdx.game.entity.item.weapon.Event.FLY_AWAY;
import static com.mygdx.game.entity.item.weapon.Event.PICK_UP;
import static com.mygdx.game.entity.item.weapon.Event.UPDATE;
import static com.mygdx.game.entity.item.weapon.State.EXPIRED;
import static com.mygdx.game.entity.item.weapon.State.INIT;
import static com.mygdx.game.entity.item.weapon.State.NOT_PICKED_UP;
import static com.mygdx.game.entity.item.weapon.State.OUTSIDER;
import static com.mygdx.game.entity.item.weapon.State.PICKED_UP;

public class LifecycleConfig implements LifecycleConfiguration<State, Event> {

    private Action<State, Event> setSizeAction;
    private Action<State, Event> setStartPositionAction;
    private Action<State, Event> updatePositionAction;
    private Action<State, Event> updateTimeElapsedAction;

    public LifecycleConfig() {
        setSizeAction = new SetSizeAction();
        setStartPositionAction = new SetStartPositionAction();
        updatePositionAction = new UpdatePositionAction();
        updateTimeElapsedAction = new UpdateTimeElapsedAction();
    }

    @Override
    public void configureTransitions(TransitionConfigurer<State, Event> configurer) {
        configurer
                .with()
                .sourceState(INIT)
                .targetState(NOT_PICKED_UP)
                .event(CREATE)
                .action(setSizeAction)
                .action(setStartPositionAction)

                .with()
                .sourceState(NOT_PICKED_UP)
                .targetState(NOT_PICKED_UP)
                .event(UPDATE)
                .action(updatePositionAction)
                .action(updateTimeElapsedAction)

                .with()
                .sourceState(NOT_PICKED_UP)
                .targetState(EXPIRED)
                .event(EXPIRE)

                .with()
                .sourceState(NOT_PICKED_UP)
                .targetState(PICKED_UP)
                .event(PICK_UP)

                .with()
                .sourceState(PICKED_UP)
                .targetState(PICKED_UP)
                .event(UPDATE)
                .action(updateTimeElapsedAction)

                .with()
                .sourceState(PICKED_UP)
                .targetState(EXPIRED)
                .event(EXPIRE)

                .with()
                .sourceState(NOT_PICKED_UP)
                .targetState(OUTSIDER)
                .event(FLY_AWAY)

                .with()
                .sourceState(PICKED_UP)
                .targetState(NOT_PICKED_UP)
                .event(DROP)

                // All bullets will be removed from the screen on the next game loop iteration
                .with()
                .sourceState(EXPIRED)
                .targetState(EXPIRED)
                .event(UPDATE)

                .with()
                .sourceState(OUTSIDER)
                .targetState(OUTSIDER)
                .event(UPDATE);
    }
}
