package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameObject;
import com.mygdx.game.GameScreen;
import com.mygdx.game.ObjectTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.mygdx.game.Assets.NUMBERS;
import static com.mygdx.game.Config.NUMBER_HEIGHT;
import static com.mygdx.game.Config.NUMBER_WIDTH;
import static com.mygdx.game.Config.VIEW_WIDTH;

/**
 * Not thread safe
 */
public class ScoreLabel implements GameObject {

    private static List<TextureRegion> numbers;

    private GameScreen gameScreen;
    private int score;
    private List<Sprite> toBeDrawn;

    private Obstacle lastPassedObstacle;

    public ScoreLabel(GameScreen screen) {
        this.gameScreen = screen;
        score = 0;

        if (numbers == null) {
            numbers = Arrays.asList(TextureRegion.split(new Texture(NUMBERS), NUMBER_WIDTH, NUMBER_HEIGHT)[0]);
        }
    }

    @Override
    public void update(float delta) {
        toBeDrawn = new ArrayList<>();
        LinkedList<Obstacle> obstacles = gameScreen.getObstacles();

        obstacles.stream()
                .filter(o -> !o.isPassed())
                .forEach(o -> {
                    if (o.getX() < gameScreen.getPlayer().getX()) {
                        score++;
                        o.setPassed(true);
                    }
                });

        if (!obstacles.isEmpty()) {
            Obstacle last = obstacles.getLast();
            if (last.getX() < gameScreen.getPlayer().getX() &&
                    last != lastPassedObstacle) {
                lastPassedObstacle = last;
                score++;
            }
        }

        char[] chars = String
                .valueOf(score)
                .toCharArray();

        List<Integer> categories = new ArrayList<>();

        for (char c : chars) {
            categories.add(Integer.valueOf(String.valueOf(c)));
        }

        float startPosition = VIEW_WIDTH / 2 - (categories.size() * NUMBER_WIDTH * 0.8f / 2);

        for (int i = 0; i < categories.size(); i++) {
            Sprite sprite = new Sprite(numbers.get(categories.get(i)));
            sprite.setPosition(startPosition + (i * NUMBER_WIDTH * 0.8f), 400);
            toBeDrawn.add(sprite);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (Sprite sprite : toBeDrawn) {
            sprite.draw(batch);
        }
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        return false;
    }

    @Override
    public Rectangle getCollider() {
        return null;
    }

    @Override
    public ObjectTag getTag() {
        return null;
    }
}
