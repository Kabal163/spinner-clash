package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class RepeatableActor extends BaseActor {

    private int sourceX = 0;

    public RepeatableActor(Texture texture) {
        super(texture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            sourceX += 5;
            batch.draw(
                    region.getTexture(),
                    0,
                    0,
                    sourceX,
                    0,
                    (int)getWidth(),
                    (int)getHeight());
        }
    }
}
