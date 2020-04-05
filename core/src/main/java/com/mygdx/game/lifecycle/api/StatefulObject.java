package com.mygdx.game.lifecycle.api;

public interface StatefulObject<S> {

    S getState();

    void setState(S state);
}
