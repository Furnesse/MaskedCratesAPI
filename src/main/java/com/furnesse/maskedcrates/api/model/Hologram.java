package com.furnesse.maskedcrates.api.model;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public interface Hologram {
    ArmorStand getArmorStand();

    Hologram createHologram(Location location);

    void remove();
}
