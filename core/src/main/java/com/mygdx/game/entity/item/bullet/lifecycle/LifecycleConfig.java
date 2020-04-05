package com.mygdx.game.entity.item.bullet.lifecycle;

import com.mygdx.game.entity.item.bullet.BulletEvent;
import com.mygdx.game.entity.item.bullet.BulletState;
import com.mygdx.game.entity.item.bullet.lifecycle.action.AddBulletToGameScreenAction;
import com.mygdx.game.entity.item.bullet.lifecycle.action.SetAnimationAction;
import com.mygdx.game.entity.item.bullet.lifecycle.action.SetSpriteAction;
import com.mygdx.game.entity.item.bullet.lifecycle.action.SetStartPositionAction;
import com.mygdx.game.entity.item.bullet.lifecycle.action.SetVelocityAction;
import com.mygdx.game.entity.item.bullet.lifecycle.action.UpdatePosition;
import com.mygdx.game.lifecycle.TransitionConfigurer;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.LifecycleConfiguration;

import static com.mygdx.game.entity.item.bullet.BulletEvent.CREATE;
import static com.mygdx.game.entity.item.bullet.BulletEvent.FLY_AWAY;
import static com.mygdx.game.entity.item.bullet.BulletEvent.HIT;
import static com.mygdx.game.entity.item.bullet.BulletEvent.UPDATE;
import static com.mygdx.game.entity.item.bullet.BulletState.INIT;
import static com.mygdx.game.entity.item.bullet.BulletState.LANDED;
import static com.mygdx.game.entity.item.bullet.BulletState.MOVING;
import static com.mygdx.game.entity.item.bullet.BulletState.OUT_OF_SCREEN;

public class LifecycleConfig implements LifecycleConfiguration<BulletState, BulletEvent> {

    private Action<BulletState, BulletEvent> setStartPositionAction;
    private Action<BulletState, BulletEvent> setVelocityAction;
    private Action<BulletState, BulletEvent> setAnimationAction;
    private Action<BulletState, BulletEvent> setSpriteAction;
    private Action<BulletState, BulletEvent> updatePosition;
    private Action<BulletState, BulletEvent> addBulletToGameScreenAction;

    public LifecycleConfig() {
        setStartPositionAction = new SetStartPositionAction();
        setVelocityAction = new SetVelocityAction();
        setAnimationAction = new SetAnimationAction();
        setSpriteAction = new SetSpriteAction();
        updatePosition = new UpdatePosition();
        addBulletToGameScreenAction = new AddBulletToGameScreenAction();
    }

    @Override
    public void configureTransitions(TransitionConfigurer<BulletState, BulletEvent> configurer) {
        configurer
                .with()
                .sourceState(INIT)
                .targetState(MOVING)
                .event(CREATE)
                .action(setVelocityAction)
                .action(setSpriteAction)
                .action(setStartPositionAction)
                .action(setAnimationAction)
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
                .targetState(OUT_OF_SCREEN)
                .event(FLY_AWAY);
    }
}
