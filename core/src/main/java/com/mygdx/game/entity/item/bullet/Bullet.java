package com.mygdx.game.entity.item.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameUtil;
import com.mygdx.game.SpinnerGame;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.ObjectTag;
import com.mygdx.game.lifecycle.api.LifecycleManager;
import com.mygdx.game.lifecycle.api.StatefulObject;
import lombok.Getter;
import lombok.Setter;

import static com.mygdx.game.Assets.BULLET;
import static com.mygdx.game.entity.item.bullet.BulletEvent.CREATE;
import static com.mygdx.game.entity.item.bullet.BulletEvent.FLY_AWAY;
import static com.mygdx.game.entity.item.bullet.BulletEvent.HIT;
import static com.mygdx.game.entity.item.bullet.BulletEvent.UPDATE;
import static com.mygdx.game.entity.item.bullet.BulletState.INIT;
import static java.util.Collections.singletonMap;
import static com.mygdx.game.entity.item.bullet.lifecycle.Constants.DELTA;

@Setter
@Getter
public class Bullet implements GameObject, StatefulObject<BulletState> {

    public final SpinnerGame gameContext;

    public static TextureRegion texture;
    private LifecycleManager<BulletState, BulletEvent> lifecycleManager;
    private float velocity;
    private Animation<TextureRegion> animation;
    private float stateTime;
    private Sprite sprite;

    private BulletState state;

    public Bullet(SpinnerGame gameContext) {
        if (texture == null) {
            texture = new TextureRegion(new Texture(BULLET));
        }
        this.gameContext = gameContext;
        stateTime = 0;
        state = INIT;
    }

    @Override
    public void create() {
        lifecycleManager = gameContext.lifecycleManagerFactory.newInstance(this.getClass());
        lifecycleManager.execute(this, CREATE);
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        lifecycleManager.execute(this, UPDATE, singletonMap(DELTA, delta));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.getKeyFrame(stateTime), sprite.getX(), sprite.getY());
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        return GameUtil.isCollided(this, anotherObject);
    }

    @Override
    public Rectangle getCollider() {
        return sprite.getBoundingRectangle();
    }

    @Override
    public ObjectTag getTag() {
        return ObjectTag.BULLET;
    }

    /**
     * Bullet has successfully hit an enemy spaceship
     */
    public void hit() {
        lifecycleManager.execute(this, HIT);
    }

    /**
     * Bullet has gone outside of the screen view
     */
    public void dismiss() {
        lifecycleManager.execute(this, FLY_AWAY);
    }

    @Override
    public BulletState getState() {
        return state;
    }

    @Override
    public void setState(BulletState state) {
        this.state = state;
    }
}
