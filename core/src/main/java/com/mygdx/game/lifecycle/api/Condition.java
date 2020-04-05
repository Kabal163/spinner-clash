package com.mygdx.game.lifecycle.api;

public interface Condition<S, E> {

    boolean evaluate(StateContext<S, E> context);
}
