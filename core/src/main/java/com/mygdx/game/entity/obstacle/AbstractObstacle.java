package com.mygdx.game.entity.obstacle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameUtil;
import com.mygdx.game.SpinnerGame;
import com.mygdx.game.entity.Acceleratable;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.ObjectTag;
import com.mygdx.game.entity.Outsider;
import com.mygdx.game.entity.Size2D;
import com.mygdx.game.lifecycle.api.LifecycleManager;
import com.mygdx.game.lifecycle.api.StatefulObject;
import lombok.Getter;
import lombok.Setter;

import static com.mygdx.game.Config.DEFAULT_OBSTACLE_VELOCITY;
import static com.mygdx.game.entity.ObjectTag.OBSTACLE;
import static com.mygdx.game.entity.obstacle.Event.CREATE;
import static com.mygdx.game.entity.obstacle.Event.EXPLODE;
import static com.mygdx.game.entity.obstacle.Event.FLY_AWAY;
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
public abstract class AbstractObstacle implements
        GameObject,
        StatefulObject<State>,
        Acceleratable,
        Outsider,
        Size2D {

    protected final SpinnerGame gameContext;

    protected LifecycleManager<State, Event> lifecycleManager;
    protected float velocity;
    protected Sprite sprite;
    protected float stateTime;
    protected float lifeTime;
    protected State state;
    protected Vector2 position;
    protected float width;
    protected float height;

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

    @Override
    public boolean isOutsider() {
        return OUTSIDER.equals(state);
    }

    @Override
    public void markOutsider() {
        lifecycleManager.execute(this, FLY_AWAY);
    }

    @Override
    public void accelerate(float delta) {
        this.velocity += delta;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(position);
    }

    @Override
    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        sprite.setPosition(position.x, position.y);
    }

    @Override
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        sprite.setSize(width, height);
    }

    public boolean isPassed() {
        return PASSED.equals(state);
    }

    public void pass() {
        lifecycleManager.execute(this, PASS);
    }

    public void explode() {
        lifecycleManager.execute(this, EXPLODE);
    }

    public boolean isExploded() {
        return EXPLODED.equals(state);
    }

    public abstract int getScore();

    public abstract TextureRegion getTexture();
}
