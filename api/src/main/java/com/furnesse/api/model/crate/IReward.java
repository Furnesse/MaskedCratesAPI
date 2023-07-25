package com.furnesse.api.model.crate;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IReward {
    /**
     * Give the reward to the player
     *
     * @param player The player to give the reward to
     */
    void give(Player player);

    /**
     * @return The ID of the reward
     */
    String getId();

    /**
     * @return The display name of the reward
     */
    ItemStack getDisplayItem();

    /**
     * @return The actions to perform when the reward is given
     */
    List<String> getActions();

    /**
     * @return The chance of the reward being given
     */
    int getChance();
}
