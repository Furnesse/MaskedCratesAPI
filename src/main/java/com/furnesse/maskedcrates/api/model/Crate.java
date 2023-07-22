package com.furnesse.maskedcrates.api.model;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Crate {
    Hologram createHologram(Location location);

    boolean hasBroadcastMessage();

    ItemStack getCrateItem();

    Reward getReward(String rewardId);

    String getBroadcastMessage();

    String getId();

    String getCrateName();

    List<String> getHologramLines();

    List<Reward> getRewards();
}
