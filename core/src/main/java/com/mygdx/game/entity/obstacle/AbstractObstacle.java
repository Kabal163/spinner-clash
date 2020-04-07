package com.mygdx.game.entity.obstacle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameUtil;
import com.mygdx.game.SpinnerGame;
import com.mygdx.game.entity.Acceleratable;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.ObjectTag;
import com.mygdx.game.lifecycle.api.LifecycleManager;
import com.mygdx.game.lifecycle.api.StatefulObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

import static com.mygdx.game.Config.DEFAULT_OBSTACLE_VELOCITY;
import static com.mygdx.game.entity.ObjectTag.OBSTACLE;
import static com.mygdx.game.entity.obstacle.Event.CREATE;
import static com.mygdx.game.entity.obstacle.Event.EXPLODE;
import static com.mygdx.game.entity.obstacle.Event.PASS;
import static com.mygdx.game.entity.obstacle.Event.UPDATE;
import static com.mygdx.game.entity.obstacle.State.EXPLODED;
import static com.mygdx.game.entity.obstacle.State.INIT;
import static com.mygdx.game.entity.obstacle.State.OUTSIDER;
import static com.mygdx.game.entity.obstacle.State.PASSED;
import static com.mygdx.game.entity.obstacle.lifecycle.Constants.DELTA;
import static java.util.Collections.singletonMap;

@Getter
@Setter
public abstract class AbstractObstacle implements GameObject, StatefulObject<State>, Acceleratable {

    protected final SpinnerGame gameContext;

    protected LifecycleManager<State, Event> lifecycleManager;
    protected float velocity;
    protected Sprite sprite;
    protected float stateTime;
    protected float lifeTime;
    protected State state;

    public AbstractObstacle(SpinnerGame gameContext) {
        this.gameContext = gameContext;
        this.lifecycleManager = gameContext.lifecycleManagerFactory.newInstance(this.getClass());
        velocity = DEFAULT_OBSTACLE_VELOCITY;
        stateTime = 0;
        lifeTime = 0;
        sprite = new Sprite(getTexture());
        state = INIT;
    }

    @Override
    public void create() {
        lifecycleManager.execute(this, CREATE);
    }

    @Override
    public void update(float delta) {
        lifecycleManager.execute(this, UPDATE, singletonMap(DELTA, delta));
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
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
        return OBSTACLE;
    }

    public void accelerate(float delta) {
        this.velocity += delta;
    }

    public float getX() {
        return sprite.getX();
    }

    public boolean isPassed() {
        return PASSED.equals(state);
    }

    public void pass() {
        lifecycleManager.execute(this, PASS);
    }

    public abstract int getScore();

    public void explode() {
        lifecycleManager.execute(this, EXPLODE);
    }

    public boolean isExploded() {
        return EXPLODED.equals(state);
    }

    @Override
    public boolean isOutOfGame() {
        return OUTSIDER.equals(state);
    }

    public abstract TextureRegion getTexture();
}
