package com.furnesse.maskedcrates.api;

import com.furnesse.maskedcrates.api.model.Crate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class MaskedCratesAPI {
    // Use a list or a map to store registered plugins
    private List<MaskedCratesExtension> registeredPlugins = new ArrayList<>();

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

    public abstract int getCratesOpened(Player player, Crate crate);

    public abstract Crate getCrate(String s);

    public abstract Crate getCrate(ItemStack itemStack);

    public abstract void openCrate(Player player, Crate crate);
}