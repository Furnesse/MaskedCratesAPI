package com.furnesse.api.model.crate;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IReward {
    void give(Player player);

    String getId();

    ItemStack getDisplayItem();

    List<String> getActions();

    int getChance();

    int getAmount();
}
