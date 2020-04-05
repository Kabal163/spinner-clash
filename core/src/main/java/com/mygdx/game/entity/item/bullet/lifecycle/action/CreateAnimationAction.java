package com.mygdx.game.entity.item.bullet.lifecycle.action;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entity.item.bullet.Bullet;
import com.mygdx.game.entity.item.bullet.Event;
import com.mygdx.game.entity.item.bullet.State;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

import static com.mygdx.game.Config.BULLET_HEIGHT;
import static com.mygdx.game.Config.BULLET_WIDTH;
import static com.mygdx.game.Config.DEFAULT_FRAME_LENGTH;

public class CreateAnimationAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        Bullet bullet = context.getStatefulObject();
        bullet.setAnimation(
                new Animation<>(
                        DEFAULT_FRAME_LENGTH,
                        TextureRegion.split(
                                Bullet.texture.getTexture(),
                                BULLET_WIDTH,
                                BULLET_HEIGHT)[0]));
    }
}
