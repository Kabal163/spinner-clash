package com.mygdx.game;

import com.mygdx.game.lifecycle.api.LifecycleManager;

public interface LifecycleManagerFactory {

    <T, S, E> LifecycleManager<S, E> newInstance(Class<T> forClass);
}
