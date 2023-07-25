package com.furnesse.api;

public interface MaskedCratesExtension {
    /**
     * Method for plugin to register effects that can be used in animations
     */
    void registerEffects();

    /**
     * Method for plugin to register animations that can be used in crates
     */
    void registerAnimations();
}
