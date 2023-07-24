package com.furnesse.api.model.crate;

import com.furnesse.api.model.CrateAnimation;
import com.furnesse.api.model.Hologram;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface ICrate {
    String getId();

    ItemStack getCrateItem();

    String getCrateName();

    CrateAnimation getAnimation();

    List<String> getHologramLines();

    List<IReward> getRewards();

    IReward getReward(String rewardId);

    boolean canViewRewards(Player player);

    boolean canOpenCrate(Player player);
}
