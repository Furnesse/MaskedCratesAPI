package com.furnesse.exampleextension.animations;

import com.furnesse.api.model.CrateAnimation;
import com.furnesse.api.model.crate.CrateHologram;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.List;

public class ExampleAnimation extends CrateAnimation {
    public ExampleAnimation(JavaPlugin plugin, String id, int maxTicks, boolean rewardPreview, boolean forceLookAt) {
        super(plugin, id, maxTicks, rewardPreview, forceLookAt);
        loadHeadPoses();
    }

    private final List<Double> headPoses = new ArrayList<>();

    private void loadHeadPoses() {
        final double speedFactor = 0.09;
        final double damping = Math.log(1.3) / getMaxTicks();

        for (int i = 0; i < getMaxTicks(); i++) {
            double y = 1 * Math.exp(-i * damping) * (Math.sin((6.28 / getMaxTicks() * i) * i * speedFactor));
            headPoses.add(y);
        }
    }

    @Override
    public void init(CrateHologram hologram) {
        Player player = hologram.getPlayer();

        // Spawns the hologram 3 blocks in front of the player and player's eye level
        Location holoLoc = player.getEyeLocation().add(player.getLocation().getDirection().multiply(3).setY(-1.5));
        // Makes the hologram face the player
        holoLoc = holoLoc.setDirection(player.getLocation().subtract(holoLoc).toVector());

        // Spawns the hologram aka the armorstand that will be manipulated in the animate method
        hologram.spawn(holoLoc);
    }

    @Override
    public void animate(ArmorStand stand, int ticks) {
        stand.setHeadPose(new EulerAngle(0, 0, headPoses.get(ticks)));
        stand.getWorld().spawnParticle(Particle.CRIT, stand.getEyeLocation(), 1);
    }
}
