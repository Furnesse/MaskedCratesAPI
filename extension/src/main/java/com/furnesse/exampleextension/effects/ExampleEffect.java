package com.furnesse.exampleextension.effects;

import com.furnesse.api.model.CrateEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ExampleEffect extends CrateEffect {
    public ExampleEffect(Plugin plugin, String id) {
        super(plugin, id);
    }

    @Override
    public void playEffect(Player player, Location location) {
        final Location flameloc = location;
        new BukkitRunnable() {
            double phi = 0.0D;

            public void run() {
                this.phi += Math.PI / 10;
                for (double theta = 0.0D; theta <= 6.283185307179586D; theta += 0.15707963267948966D) {
                    double r = 1.5D;
                    double x = r * Math.cos(theta) * Math.sin(this.phi);
                    double y = r * Math.cos(this.phi) + 1.5D;
                    double z = r * Math.sin(theta) * Math.sin(this.phi);
                    flameloc.add(x, y, z);
                    flameloc.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, flameloc.getX(), flameloc.getY(), flameloc.getZ(), 0);
                    flameloc.subtract(x, y, z);
                }

                if (this.phi > Math.PI) {
                    cancel();
                }
            }
        }.runTaskTimer(getPlugin(), 0L, 1L);
    }
}
