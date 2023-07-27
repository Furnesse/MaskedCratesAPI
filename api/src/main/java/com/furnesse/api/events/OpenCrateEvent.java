package com.furnesse.api.events;

import com.furnesse.api.model.crate.ActiveCrate;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class OpenCrateEvent extends Event implements Cancellable {
    public enum OpenType {
        COMMAND,
        ITEM,
        MENU,
        CUSTOM
    }

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final ActiveCrate activeCrate;
    private final OpenType openType;
    private final Consumer<ActiveCrate> consumer;
    private boolean cancelled;

    public OpenCrateEvent(Player player, ActiveCrate activeCrate, OpenType openType, Consumer<ActiveCrate> consumer) {
        this.player = player;
        this.activeCrate = activeCrate;
        this.openType = openType;
        this.consumer = consumer;

        cancelled = false;
    }

    public Player getPlayer() {
        return player;
    }

    public ActiveCrate getActiveCrate() {
        return activeCrate;
    }

    public OpenType getOpenType() {
        return openType;
    }

    public Consumer<ActiveCrate> getConsumer() {
        return consumer;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }
}
