package com.furnesse.api.model.crate;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

public interface ActiveCrate {
    boolean isActive();

    /**
     * Plays the animation
     */
    void playAnimation();

    /**
     * Stops the animation
     */
    void stopAnimation();

    /**
     * Reward the player with the reward provided, if rewardPreview is true, the
     * reward will be previewed to the player before giving it to them, otherwise,
     * if rewardPreview is false, the reward will be given to the player.
     *
     * @param player The player to reward
     */
    void previewReward(Player player);

    /**
     * The reward that the player will receive when they open the crate.
     *
     * @return The reward
     */
    Reward getReward();

    /**
     * The crate that the player is opening.
     *
     * @return The crate
     */
    Crate getCrate();

    /**
     * The player that is opening the crate.
     *
     * @return The player
     */
    Player getPlayer();

    /**
     * The hologram that is being displayed to the player.
     * <p> This is the hologram that is being animated. </p>
     *
     * @return The hologram
     */
    ArmorStand getArmorStand();

    /**
     * The hologram that is being displayed to the player.
     * <p> This is the hologram that is being animated. </p>
     *
     * @param holoLoc The location to spawn the hologram
     */
    void spawn(Location holoLoc);
}
