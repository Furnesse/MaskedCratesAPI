package com.furnesse.api.model;

import com.furnesse.api.MaskedCratesAPI;
import com.furnesse.api.MaskedCratesServiceLocator;
import com.furnesse.api.events.CancelledCrateOpenEvent;
import com.furnesse.api.events.OpenedCrateEvent;
import com.furnesse.api.model.crate.ActiveCrate;
import org.bukkit.Bukkit;
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
     * @param activeCrate The activeCrate that is being animated
     */
    public void playAnimation(ActiveCrate activeCrate) {
        final Player player = activeCrate.getPlayer();

        init(activeCrate);

        final ArmorStand stand = activeCrate.getArmorStand();

        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (!player.isOnline() || !activeCrate.isActive()) {
                    activeCrate.stopAnimation();
                    cancel();
                    return;
                }

                if (ticks >= maxTicks) {
                    Bukkit.getPluginManager().callEvent(new OpenedCrateEvent(player, activeCrate, ac -> {
                        api.endAnimation(activeCrate, rewardPreview, true);
                        actionOnEnd(player, stand, ticks);
                    }));
                    cancel();
                    return;
                }

                if (forceLookAt && !faceDirection(player, stand.getEyeLocation())) {
                    CancelledCrateOpenEvent event = new CancelledCrateOpenEvent(player, activeCrate, ac -> {
                        api.endAnimation(ac, false, false);
                    }, CancelledCrateOpenEvent.Reason.TELEPORT);

                    Bukkit.getPluginManager().callEvent(event);

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
    protected abstract void init(ActiveCrate hologram);

    /**
     * Called when the animation is finished, it will reward the player and stop the
     * animation
     *
     * @param player The player that is opening the crate
     * @param stand  The armorstand that is being animated
     * @param tick   The amount of ticks the animation has been running
     */
    protected abstract void actionOnEnd(Player player, ArmorStand stand, int tick);

    /**
     * Called every tick of the animation, it is used to animate the armorstand
     * <p> The player is already facing the armorstand </p> if forceLookAt is true
     *
     * @param player The player that is opening the crate
     * @param stand  The armorstand that is being animated
     * @param tick   The amount of ticks the animation has been running
     */
    protected abstract void animate(Player player, ArmorStand stand, int tick);

    /**
     * Makes the player face the target location
     *
     * @param player The player that is opening the crate
     * @param target The location the player should face
     * @return Whether the player is still in the same world as the target
     */
    private boolean faceDirection(Player player, Location target) {
        if (!target.getWorld().equals(player.getWorld())) return false;

        Location faceTarget = player.getLocation()
                .setDirection(target.clone().subtract(player.getEyeLocation()).toVector());

        player.setRotation(faceTarget.getYaw(), faceTarget.getPitch());
        return true;
    }

    /**
     * Plays an effect at the location of the armorstand
     *
     * @param effectId   The id of the effect
     * @param player     The player that is opening the crate
     * @param armorStand The armorstand that is being animated
     */
    protected void playEffect(String effectId, Player player, ArmorStand armorStand) {
        CrateEffect effect = CrateEffect.getEffect(effectId);

        if (effect == null) {
            plugin.getLogger().warning("Effect with id " + effectId + " does not exist");
            return;
        }

        effect.playEffect(player, armorStand.getEyeLocation());
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
