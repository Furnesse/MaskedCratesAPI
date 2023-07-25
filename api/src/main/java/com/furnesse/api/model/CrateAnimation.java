package com.furnesse.api.model;

import com.furnesse.api.MaskedCratesAPI;
import com.furnesse.api.MaskedCratesServiceLocator;
import com.furnesse.api.model.crate.ICrateHologram;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public abstract class CrateAnimation {
    private static final List<CrateAnimation> animations = new ArrayList<>();

    /**
     * Gets the animation with the given id
     * <p> Returns null if the animation does not exist </p>
     * <p> The id is case insensitive </p>
     *
     * @param id The id of the animation
     * @return The animation with the given id
     */
    public static CrateAnimation getAnimation(String id) {
        for (CrateAnimation animation : animations) {
            if (animation.getId().equalsIgnoreCase(id)) {
                return animation;
            }
        }

        return null;
    }

    private final JavaPlugin plugin;
    private final MaskedCratesAPI api = MaskedCratesServiceLocator.getMaskedCratesAPI();
    private final String id;
    private final int maxTicks;
    private final boolean rewardPreview, forceLookAt;
    private double[] variables;

    public CrateAnimation(JavaPlugin plugin, String id, int maxTicks, boolean rewardPreview, boolean forceLookAt) {
        this.plugin = plugin;
        this.id = id;
        this.maxTicks = maxTicks;
        this.rewardPreview = rewardPreview;
        this.forceLookAt = forceLookAt;
        variables = new double[0];

        animations.add(this);
    }

    /**
     * Starts the animation for the player
     *
     * @param hologram The hologram that is being animated
     */
    public void playAnimation(ICrateHologram hologram) {
        final Player player = hologram.getPlayer();

        init(hologram);

        final ArmorStand stand = hologram.getArmorStand();

        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (!player.isOnline() || !hologram.isActive()) {
                    hologram.stopAnimation();
                    cancel();
                    return;
                }

                if (ticks >= maxTicks) {
                    api.endAnimation(hologram, rewardPreview, false);
                    cancel();
                    return;
                }

                if (forceLookAt && !faceDirection(player, stand.getLocation())) {
                    api.endAnimation(hologram, false, true);
                    cancel();
                    return;
                }

                animate(player, stand, ticks);

                ticks++;
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    /**
     * Called when the animation is started, it will spawn the hologram and set the
     * player's state
     *
     * @param hologram The hologram that is being animated
     */
    protected abstract void init(ICrateHologram hologram);

    /**
     * Called when the animation is finished, it will reward the player and stop the
     * animation
     *
     * @param player The player that is opening the crate
     * @param stand  The armorstand that is being animated
     * @param ticks  The amount of ticks the animation has been running
     */
    protected abstract void actionOnEnd(Player player, ArmorStand stand, int ticks);

    /**
     * Called every tick of the animation, it is used to animate the armorstand
     * <p> The player is already facing the armorstand </p> if forceLookAt is true
     *
     * @param player The player that is opening the crate
     * @param stand  The armorstand that is being animated
     * @param ticks  The amount of ticks the animation has been running
     */
    protected abstract void animate(Player player, ArmorStand stand, int ticks);

    private boolean faceDirection(Player player, Location target) {
        if (!target.getWorld().equals(player.getWorld())) return false;

        Location faceTarget = player.getLocation()
                .setDirection(target.clone().subtract(player.getEyeLocation()).toVector());

        player.setRotation(faceTarget.getYaw(), faceTarget.getPitch());
        return true;
    }

    protected void playEffect(String effectId, Player player, Location location) {
        CrateEffect effect = CrateEffect.getEffect(effectId);

        if (effect == null) {
            plugin.getLogger().warning("Effect with id " + effectId + " does not exist");
            return;
        }

        effect.playEffect(player, location);
    }

    protected void setVariables(double... values) {
        this.variables = values;
    }

    protected int getMaxTicks() {
        return maxTicks;
    }

    protected double[] getVariables() {
        return variables;
    }

    public String getId() {
        return id;
    }

    public boolean isRewardPreview() {
        return rewardPreview;
    }

    public boolean isForceLookAt() {
        return forceLookAt;
    }
}
