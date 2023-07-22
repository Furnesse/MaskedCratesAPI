package com.furnesse.maskedcrates.api.model;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Reward {
    void give(Player player);

    ItemStack getDisplayItem();

    List<String> getActions();

    int getChance();

    String getRewardMessage();

    String getRewardName();

    int getAmount();
}
