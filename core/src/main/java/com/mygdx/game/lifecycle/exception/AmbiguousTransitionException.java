package com.mygdx.game.lifecycle.exception;

public class AmbiguousTransitionException extends TransitionException {

    public AmbiguousTransitionException() {
    }

    public AmbiguousTransitionException(String message) {
        super(message);
    }
}
