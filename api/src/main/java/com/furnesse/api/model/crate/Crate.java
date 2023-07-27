package com.furnesse.api.model.crate;

import com.furnesse.api.model.CrateAnimation;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Crate {
    boolean hasBroadcastMessage();

    String getBroadcastMessage();

    ItemStack getCrateItem();

    String getCrateName();

    CrateAnimation getAnimation();

    List<String> getHologramLines();

    List<Reward> getRewards();

    Reward getReward(String rewardId);

    boolean canViewRewards(Player player);

    boolean canOpenCrate(Player player);

    boolean isForceLookAt();

    void setCrateName(String name);
    void setRewards(List<Reward> rewards);
}
