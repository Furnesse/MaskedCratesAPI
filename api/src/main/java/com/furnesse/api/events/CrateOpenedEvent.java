package com.furnesse.api.events;

import com.furnesse.api.model.crate.ICrateHologram;
import com.furnesse.api.model.crate.ICrate;
import com.furnesse.api.model.crate.IReward;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CrateOpenedEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final ICrateHologram crateHologram;
    private final boolean rewardPreview;

    private boolean cancelled;

    public CrateOpenedEvent(ICrateHologram crateHologram, boolean rewardPreview) {
        this.crateHologram = crateHologram;
        this.rewardPreview = rewardPreview;
        this.cancelled = false;
    }


    public Player getPlayer() {
        return crateHologram.getPlayer();
    }

    public ICrate getCrate() {
        return crateHologram.getCrate();
    }

    public IReward getReward() {
        return crateHologram.getReward();
    }

    public boolean isRewardPreview() {
        return rewardPreview;
    }

    public ICrateHologram getCrateHologram() {
        return crateHologram;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = true;
    }
}
