package com.furnesse.api.model;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public abstract class CrateEffect {
    private final static List<CrateEffect> effects = new ArrayList<>();

    public static CrateEffect getEffect(String id) {
        for (CrateEffect effect : effects) {
            if (effect.getId().equalsIgnoreCase(id)) {
                return effect;
            }
        }
        return null;
    }

    private final Plugin plugin;
    private final String id;

    public CrateEffect(Plugin plugin, String id) {
        this.plugin = plugin;
        this.id = id;

        effects.add(this);
    }

    public abstract void playEffect(Player player, Location location);

    public String getId() {
        return id;
    }

    protected Plugin getPlugin() {
        return plugin;
    }
}
