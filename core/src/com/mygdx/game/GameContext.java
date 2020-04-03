package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.entity.Explosion;
import com.mygdx.game.entity.GameOverLabel;
import com.mygdx.game.entity.Obstacle;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.ScoreLabel;
import lombok.Data;

import java.util.LinkedList;

@Data
public class GameContext {

    public Actor background;
    public Player player;
    public Explosion explosion;
    public LinkedList<Obstacle> obstacles;
    public ScoreLabel scoreLabel;
    public GameOverLabel gameOverLabel;
}
