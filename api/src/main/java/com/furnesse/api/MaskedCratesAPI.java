package com.furnesse.api;

import com.furnesse.api.model.CrateAnimation;
import com.furnesse.api.model.CrateEffect;
import com.furnesse.api.model.crate.ICrate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MaskedCratesAPI {
    // Use a list or a map to store registered plugins
    private final List<MaskedCratesExtension> registeredPlugins = new ArrayList<>();

    // Method for plugins to register themselves
    public void registerPlugin(MaskedCratesExtension plugin) {
        registeredPlugins.add(plugin);
    }

    // Method for initialization, call this when your plugin initializes
    public void initRegisteredPlugins() {
        for (MaskedCratesExtension plugin : registeredPlugins) {
            plugin.registerEffects();
            plugin.registerAnimations();
        }
    }

    // Method for shutdown, call this when your plugin shuts down
    public void shutdownRegisteredPlugins() {
        for (MaskedCratesExtension plugin : registeredPlugins) {
            // Call shutdown methods or perform cleanup as needed
        }
    }

    // Use lists to store registered effects and animations
    private final List<CrateEffect> registeredEffects = new ArrayList<>();
    private final List<CrateAnimation> registeredAnimations = new ArrayList<>();

    // Method for plugins to register effects
    public void registerEffect(CrateEffect effect) {
        registeredEffects.add(effect);
    }

    // Method for plugins to register animations
    public void registerAnimation(CrateAnimation animation) {
        registeredAnimations.add(animation);
    }

    // Method to retrieve all registered effects
    public List<CrateEffect> getRegisteredEffects() {
        return Collections.unmodifiableList(registeredEffects);
    }

    // Method to retrieve all registered animations
    public List<CrateAnimation> getRegisteredAnimations() {
        return Collections.unmodifiableList(registeredAnimations);
    }

    public abstract int getCratesOpened(Player player, ICrate crate);

    public abstract ICrate getCrate(String s);

    public abstract ICrate getCrate(ItemStack itemStack);

    public abstract void openCrate(Player player, ICrate crate);
}