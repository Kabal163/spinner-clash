package com.mygdx.game.entity.item.weapon;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameUtil;
import com.mygdx.game.SpinnerGame;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.ObjectTag;
import com.mygdx.game.entity.item.PickUpItem;
import com.mygdx.game.lifecycle.api.LifecycleManager;
import com.mygdx.game.lifecycle.api.StatefulObject;
import lombok.Getter;
import lombok.Setter;

import static com.mygdx.game.Config.WEAPON_LIFE_LENGTH;
import static com.mygdx.game.Config.WEAPON_VELOCITY;
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
import static com.mygdx.game.entity.item.weapon.lifecycle.Constants.DELTA;
import static java.util.Collections.singletonMap;

@Getter
@Setter
public abstract class AbstractWeapon implements PickUpItem, StatefulObject<State> {

    protected final SpinnerGame gameContext;

    protected LifecycleManager<State, Event> lifecycleManager;
    protected State state;
    protected Sprite sprite;
    protected float velocity;
    protected float timeElapsed;
    protected Vector2 position;
    protected float width;
    protected float height;


    public AbstractWeapon(SpinnerGame gameContext) {
        this.gameContext = gameContext;
        lifecycleManager = gameContext.lifecycleManagerFactory.newInstance(this.getClass());
        sprite = new Sprite(getTexture());
        velocity = WEAPON_VELOCITY;

        state = INIT;
    }

    @Override
    public void create() {
        lifecycleManager.execute(this, CREATE);
    }

    @Override
    public void update(float delta) {
        timeElapsed += delta;

        if (timeElapsed >= WEAPON_LIFE_LENGTH) {
            lifecycleManager.execute(this, EXPIRE);
            return;
        }

        lifecycleManager.execute(this, UPDATE, singletonMap(DELTA, delta));
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (NOT_PICKED_UP.equals(state)) {
            sprite.draw(batch);
        }
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
        return null;
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
    public boolean isExpired() {
        return EXPIRED.equals(state);
    }

    @Override
    public void pickUp() {
        lifecycleManager.execute(this, PICK_UP);
    }

    @Override
    public boolean isPickedUp() {
        return PICKED_UP.equals(state);
    }

    @Override
    public void drop() {
        lifecycleManager.execute(this, DROP);
    }

    public abstract TextureRegion getTexture();

    @Override
    public Vector2 getPosition() {
        return new Vector2(position);
    }

    @Override
    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        sprite.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        sprite.setSize(width, height);
    }
}
