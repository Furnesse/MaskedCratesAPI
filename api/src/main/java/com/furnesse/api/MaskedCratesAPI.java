package com.furnesse.api;

import com.furnesse.api.model.crate.ActiveCrate;
import com.furnesse.api.model.crate.Crate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
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

    /**
     * Returns the amount of crates a player has opened of a certain type
     *
     * @param player The player to check
     * @param crate  The crate to check
     * @return The amount of crates the player has opened
     */
    public abstract int getCratesOpened(Player player, Crate crate);

    /**
     * Returns the crate with the given name or null if it doesn't exist
     * <p> The name is case insensitive </p>
     *
     * @param s The name of the crate
     * @return The crate with the given name or null if it doesn't exist
     */
    public abstract Crate getCrate(String s);

    /**
     * Returns the crate that is represented by the given item stack
     * <p> Returns null if the item stack doesn't represent a crate </p>
     *
     * @param itemStack The item stack to check
     * @return The crate that is represented by the given item stack
     */
    public abstract Crate getCrate(ItemStack itemStack);

    /**
     * Opens the given crate for the given player
     *
     * @param player The player to open the crate for
     * @param crate  The crate to open
     */
    public abstract void openCrate(Player player, Crate crate);

    /**
     * Ends the animation of an active crate.
     * <p> This method is called when the animation is finished or when the player leaves the server </p>
     * rewardPreview is only shown if both rewardPreview and rewardPlayer are true
     *
     * @param crateHologram The active crate to end the animation for
     * @param rewardPreview Whether it should preview the reward
     * @param rewardPlayer  Whether the player should be rewarded
     */
    public abstract void endAnimation(ActiveCrate crateHologram, boolean rewardPreview, boolean rewardPlayer);
}