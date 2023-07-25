package com.furnesse.api.events;

import com.furnesse.api.model.crate.ICrateHologram;
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
    private final ICrateHologram crateHologram;
    private boolean cancelled;

    public OpenCrateEvent(CommandSender activator, ICrateHologram crateHologram) {
        this.activator = activator;
        this.crateHologram = crateHologram;
        this.cancelled = false;
    }

    /**
     * @return the command sender that activated the crate opening sequence
     */
    public CommandSender getActivator() {
        return activator;
    }

    /**
     * @return the player that is opening the crate
     */
    public Player getPlayer() {
        return crateHologram.getPlayer();
    }

    /**
     * @return the crate that is being opened
     */
    public ICrate getCrate() {
        return crateHologram.getCrate();
    }

    /**
     * @return the hologram that is being animated
     */
    public ICrateHologram getCrateHologram() {
        return crateHologram;
    }

    /**
     * @return whether or not the crate opening sequence is cancelled
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets whether or not the crate opening sequence is cancelled, will end the crate opening sequence if cancelled
     *
     * @param cancelled whether or not the crate opening sequence is cancelled
     */
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
