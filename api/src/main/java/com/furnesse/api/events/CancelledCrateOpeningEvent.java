package com.furnesse.api.events;

import com.furnesse.api.model.crate.ICrateHologram;
import com.furnesse.api.model.crate.ICrate;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CancelledCrateOpeningEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final ICrateHologram crateHologram;

    public CancelledCrateOpeningEvent(ICrateHologram crateHologram) {
        this.crateHologram = crateHologram;
    }

    public ICrateHologram getCrateHologram() {
        return crateHologram;
    }

    public Player getPlayer() {
        return crateHologram.getPlayer();
    }

    public ICrate getCrate() {
        return crateHologram.getCrate();
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
