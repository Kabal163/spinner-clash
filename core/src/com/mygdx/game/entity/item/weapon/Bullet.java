package com.mygdx.game.entity.item.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameScreen;
import com.mygdx.game.GameUtil;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.ObjectTag;

import static com.mygdx.game.Assets.BULLET;
import static com.mygdx.game.Config.DEFAULT_BULLET_VELOCITY;

public class Bullet implements GameObject {

    private static TextureRegion texture;
    private final GameScreen gameScreen;
    private float velocity;
    private Animation<TextureRegion> animation;
    private float stateTime;
    private Sprite sprite;
    private boolean hit;

    private float x;
    private float y;

    public Bullet(GameScreen gameScreen) {
        if (texture == null) {
            texture = new TextureRegion(new Texture(BULLET));
        }
        this.gameScreen = gameScreen;
        animation = new Animation<>(0.2f, TextureRegion.split(texture.getTexture(), 45, 34)[0]);
        velocity = DEFAULT_BULLET_VELOCITY;
        stateTime = 0;
        setPosition(gameScreen);

        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        x = x + velocity * delta;
        sprite.setX(x);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.getKeyFrame(stateTime), x, y);
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

    public void hit() {
        hit = true;
    }

    public boolean isHit() {
        return hit;
    }

    private void setPosition(GameScreen gameScreen) {
        Rectangle player = gameScreen.getPlayer().getCollider();

        x = player.getX() + player.getWidth() / 2;
        y = player.getY() + player.getHeight() / 3;
    }
}
