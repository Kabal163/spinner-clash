package com.mygdx.game.entity.item.bullet.lifecycle;

import com.mygdx.game.entity.item.bullet.Event;
import com.mygdx.game.entity.item.bullet.State;
import com.mygdx.game.entity.item.bullet.lifecycle.action.AddBulletToGameScreenAction;
import com.mygdx.game.entity.item.bullet.lifecycle.action.CreateAnimationAction;
import com.mygdx.game.entity.item.bullet.lifecycle.action.CreateSpriteAction;
import com.mygdx.game.entity.item.bullet.lifecycle.action.SetStartPositionAction;
import com.mygdx.game.entity.item.bullet.lifecycle.action.SetVelocityAction;
import com.mygdx.game.entity.item.bullet.lifecycle.action.UpdatePositionAction;
import com.mygdx.game.lifecycle.TransitionConfigurer;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.LifecycleConfiguration;

import static com.mygdx.game.entity.item.bullet.Event.CREATE;
import static com.mygdx.game.entity.item.bullet.Event.FLY_AWAY;
import static com.mygdx.game.entity.item.bullet.Event.HIT;
import static com.mygdx.game.entity.item.bullet.Event.UPDATE;
import static com.mygdx.game.entity.item.bullet.State.INIT;
import static com.mygdx.game.entity.item.bullet.State.LANDED;
import static com.mygdx.game.entity.item.bullet.State.MOVING;
import static com.mygdx.game.entity.item.bullet.State.OUT_OF_GAME;

public class LifecycleConfig implements LifecycleConfiguration<State, Event> {

    private Action<State, Event> setStartPositionAction;
    private Action<State, Event> setVelocityAction;
    private Action<State, Event> createAnimationAction;
    private Action<State, Event> createSpriteAction;
    private Action<State, Event> updatePosition;
    private Action<State, Event> addBulletToGameScreenAction;

    public LifecycleConfig() {
        setStartPositionAction = new SetStartPositionAction();
        setVelocityAction = new SetVelocityAction();
        createAnimationAction = new CreateAnimationAction();
        createSpriteAction = new CreateSpriteAction();
        updatePosition = new UpdatePositionAction();
        addBulletToGameScreenAction = new AddBulletToGameScreenAction();
    }

    @Override
    public void configureTransitions(TransitionConfigurer<State, Event> configurer) {
        configurer
                .with()
                .sourceState(INIT)
                .targetState(MOVING)
                .event(CREATE)
                .action(setVelocityAction)
                .action(createSpriteAction)
                .action(setStartPositionAction)
                .action(createAnimationAction)
                .action(addBulletToGameScreenAction)

                .with()
                .sourceState(MOVING)
                .targetState(MOVING)
                .event(UPDATE)
                .action(updatePosition)

                .with()
                .sourceState(MOVING)
                .targetState(LANDED)
                .event(HIT)

                .with()
                .sourceState(MOVING)
                .targetState(OUT_OF_GAME)
                .event(FLY_AWAY);
    }
}
