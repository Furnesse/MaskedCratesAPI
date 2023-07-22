package com.furnesse.maskedcrates.api;

import com.furnesse.maskedcrates.api.model.Crate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface MaskedCratesAPI {
    int getCratesOpened(Player player, Crate crate);

    Crate getCrate(String crateId);

    Crate getCrate(ItemStack stack);

    void openCrate(Player player, Crate crate);
}