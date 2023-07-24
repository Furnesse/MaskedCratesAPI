package com.furnesse.api.model.crate;

import com.furnesse.api.model.Hologram;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

public interface CrateHologram {
    boolean isActive();

    void playAnimation();

    void stopAnimation();

    void rewardPlayer(Player player, boolean rewardPreview);

    IReward getReward();

    ICrate getCrate();

    Player getPlayer();

    ArmorStand getArmorStand();

    Hologram spawn(Location holoLoc);
}
