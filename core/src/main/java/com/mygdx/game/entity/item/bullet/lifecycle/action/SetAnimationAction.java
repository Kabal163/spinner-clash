package com.mygdx.game.entity.item.bullet.lifecycle.action;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entity.item.bullet.Bullet;
import com.mygdx.game.entity.item.bullet.BulletEvent;
import com.mygdx.game.entity.item.bullet.BulletState;
import com.mygdx.game.lifecycle.api.Action;
import com.mygdx.game.lifecycle.api.StateContext;

import static com.mygdx.game.Config.BULLET_HEIGHT;
import static com.mygdx.game.Config.BULLET_WIDTH;
import static com.mygdx.game.Config.DEFAULT_FRAME_LENGTH;

public class SetAnimationAction implements Action<BulletState, BulletEvent> {

    @Override
    public void execute(StateContext<BulletState, BulletEvent> context) {
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
