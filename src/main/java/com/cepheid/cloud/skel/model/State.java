package com.cepheid.cloud.skel.model;

public enum State {
    UNDEFINED,
    VALID,
    INVALID;

    State() {}

    public static boolean contains(String test) {
        for (State c : State.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
