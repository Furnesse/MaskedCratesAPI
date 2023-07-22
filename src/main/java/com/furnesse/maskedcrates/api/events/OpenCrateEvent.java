package com.furnesse.maskedcrates.api.events;

import com.furnesse.maskedcrates.api.model.Crate;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OpenCrateEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final CommandSender activator;
    private final Player player;
    private final Crate crate;
    private boolean cancelled;

    public OpenCrateEvent(CommandSender activator, Player player, Crate crate) {
        this.activator = activator;
        this.player = player;
        this.crate = crate;
        this.cancelled = false;
    }

    public CommandSender getActivator() {
        return activator;
    }

    public Player getPlayer() {
        return player;
    }

    public Crate getCrate() {
        return crate;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
