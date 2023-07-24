package com.furnesse.api.model;

import com.furnesse.api.model.crate.CrateHologram;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class CrateAnimation {
    private final JavaPlugin plugin;
    private final String id;
    private final int maxTicks;
    private final boolean rewardPreview, forceLookAt;

    public CrateAnimation(JavaPlugin plugin, String id, int maxTicks, boolean rewardPreview, boolean forceLookAt) {
        this.plugin = plugin;
        this.id = id;
        this.maxTicks = maxTicks;
        this.rewardPreview = rewardPreview;
        this.forceLookAt = forceLookAt;
    }

    public void playAnimation(CrateHologram hologram) {
        Player player = hologram.getPlayer();
        setPlayerState(player);

        init(hologram);

        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (!player.isOnline()) {
                    hologram.stopAnimation();
                    cancel();
                    return;
                }

                if (ticks >= maxTicks) {
                    hologram.stopAnimation();
                    hologram.rewardPlayer(player, rewardPreview);
                    cancel();
                    return;
                }

                if (forceLookAt && !faceDirection(player, hologram.getArmorStand().getLocation())) {
                    hologram.stopAnimation();
                    hologram.rewardPlayer(player, false);
                    cancel();
                    return;
                }

                animate(hologram.getArmorStand(), ticks);

                ticks++;
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    protected abstract void init(CrateHologram hologram);

    protected abstract void animate(ArmorStand stand, int ticks);

    private boolean faceDirection(Player player, Location target) {
        if (!target.getWorld().equals(player.getWorld())) return false;

        Location faceTarget = player.getLocation()
                .setDirection(target.clone().subtract(player.getEyeLocation()).toVector());

        player.setRotation(faceTarget.getYaw(), faceTarget.getPitch());
        return true;
    }

    private void setPlayerState(Player player) {
        if (forceLookAt) {
            player.setMetadata("canmove", new FixedMetadataValue(plugin, false));
        }

        if (!player.hasMetadata("opening")) {
            player.setMetadata("opening", new FixedMetadataValue(plugin, true));
        }

//        player.setInvulnerable(true);
//        player.setCanPickupItems(false);
//        player.setCollidable(false);
//        player.setMetadata("invulnerable", new FixedMetadataValue(plugin, player.isInvulnerable()));
//        player.setMetadata("pickupitems", new FixedMetadataValue(plugin, player.getCanPickupItems()));
//        player.setMetadata("collidable", new FixedMetadataValue(plugin, player.isCollidable()));
    }

    public String getId() {
        return id;
    }

    protected int getMaxTicks() {
        return maxTicks;
    }
}
