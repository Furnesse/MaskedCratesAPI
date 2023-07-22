package com.furnesse.maskedcrates.api.model;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Reward {
    void give(Player player);

    ItemStack getDisplayItem();
}
