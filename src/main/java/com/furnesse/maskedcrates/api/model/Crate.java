package com.furnesse.maskedcrates.api.model;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public interface Crate {
    Hologram createHologram(Location location);

    boolean hasBroadcastMessage();

    ItemStack getCrateItem();

    Reward getReward(String rewardId);
}
