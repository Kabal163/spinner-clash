package com.mygdx.game.lifecycle.api;


import com.mygdx.game.lifecycle.TransitionConfigurer;

public interface LifecycleConfiguration<S extends Enum<S>, E extends Enum<E>> {

    void configureTransitions(TransitionConfigurer<S, E> configurer);
}
