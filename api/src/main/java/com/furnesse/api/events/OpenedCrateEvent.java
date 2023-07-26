package com.furnesse.api.events;

import com.furnesse.api.model.crate.ActiveCrate;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class OpenedCrateEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final ActiveCrate activeCrate;

    public OpenedCrateEvent(Player player, ActiveCrate activeCrate) {
        this.player = player;
        this.activeCrate = activeCrate;
    }

    public Player getPlayer() {
        return player;
    }

    public ActiveCrate getActiveCrate() {
        return activeCrate;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
