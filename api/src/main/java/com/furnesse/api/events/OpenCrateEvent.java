package com.furnesse.api.events;

import com.furnesse.api.model.crate.ICrate;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class OpenCrateEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final CommandSender activator;
    private final Player player;
    private final ICrate crate;
    private boolean cancelled;

    public OpenCrateEvent(CommandSender activator, Player player, ICrate crate) {
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

    public ICrate getCrate() {
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
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
