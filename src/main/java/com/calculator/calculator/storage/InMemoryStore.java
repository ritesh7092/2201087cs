package com.calculator.calculator.storage;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryStore {

    private final Map<String, List<Integer>> previousSets = new HashMap<>();
    private final Map<String, List<Integer>> currentSets  = new HashMap<>();

    public List<Integer> getPreviousState(String numberId) {
        return previousSets.getOrDefault(numberId, Collections.emptyList());
    }

    public List<Integer> getCurrentState(String numberId) {
        return currentSets.getOrDefault(numberId, Collections.emptyList());
    }

    public void updateState(String numberId, List<Integer> newSet) {
        List<Integer> oldCurrent = currentSets.get(numberId);
        if (oldCurrent != null) {
            previousSets.put(numberId, oldCurrent);
        }
        currentSets.put(numberId, newSet);
    }
}
