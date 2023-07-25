package com.furnesse.api.model;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public interface Hologram {
    /**
     * @return The armorstand
     */
    ArmorStand getArmorStand();

    /**
     * Creates the hologram at the specified location
     *
     * @param location The location to create the hologram at
     * @return The hologram
     */
    Hologram createHologram(Location location);

    /**
     * Removes the hologram
     */
    void remove();
}
