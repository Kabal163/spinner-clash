package com.mygdx.game.lifecycle;

import com.mygdx.game.lifecycle.api.ContextConstants;
import com.mygdx.game.lifecycle.api.LifecycleConfiguration;
import com.mygdx.game.lifecycle.api.LifecycleManager;
import com.mygdx.game.lifecycle.api.StateContext;
import com.mygdx.game.lifecycle.api.StatefulObject;
import com.mygdx.game.lifecycle.api.TransitionResult;
import com.mygdx.game.lifecycle.exception.AmbiguousTransitionException;
import com.mygdx.game.lifecycle.exception.TransitionNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptyMap;

@Slf4j
public class LifecycleManagerImpl<S extends Enum<S>, E extends Enum<E>> implements LifecycleManager<S, E> {

    private final Set<Transition<S, E>> transitions;

    public LifecycleManagerImpl(LifecycleConfiguration<S, E> lifecycleConfiguration) {
        TransitionBuilder<S, E> transitionBuilder = new TransitionBuilderImpl<>();
        lifecycleConfiguration.configureTransitions(transitionBuilder);
        transitions = transitionBuilder.buildTransitions();
    }

    @Override
    public TransitionResult<S, E> execute(StatefulObject<S> statefulObject, E event) {
        return execute(statefulObject, event, emptyMap());
    }

    @Override
    public  TransitionResult<S, E> execute(StatefulObject<S> statefulObject, E event, Map<String, Object> variables) {
        Transition<S, E> transition = getMatchingTransition(statefulObject, event);
        StateContext<S, E> context = new StateContext<>(statefulObject, event, variables);
        boolean success = false;

        try {
           success  = transition.transit(context);
        } catch (Exception ex) {
            log.error("Error while transition from {} to {} with event {}",
                    transition.getSourceState(),
                    transition.getTargetState(),
                    event);

            context.putIfAbsentVariable(ContextConstants.ERROR_MESSAGE, ex.getMessage());
        }

        if (success) {
            statefulObject.setState(transition.getTargetState());
        }

        return new TransitionResult<>(
                success,
                context,
                transition.getSourceState(),
                transition.getTargetState());
    }

    private Transition<S, E> getMatchingTransition(StatefulObject<S> statefulObject, E event) {
        S sourceState = statefulObject.getState();

        Set<Transition<S, E>> matchTransitions = transitions.stream()
                .filter(t -> Objects.equals(t.getSourceState(), sourceState))
                .filter(t -> Objects.equals(t.getEvent(), event))
                .collect(Collectors.toSet());

        if (matchTransitions.size() > 1) {
            log.error("There is more then one transition match! Matching transitions: {}",
                    matchTransitions.stream()
                            .map(t -> t.getClass().getName())
                            .toArray());
            throw new AmbiguousTransitionException("There is more then one transition match!");
        }

        return matchTransitions.stream()
                .findFirst()
                .orElseThrow(() -> {
                    log.error("There is no matching transition for source state: {} and event: {}",
                            sourceState,
                            event);
                    return new TransitionNotFoundException("There is no matching transition!");
                });
    }
}
