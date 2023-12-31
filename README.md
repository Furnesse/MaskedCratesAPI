# MaskedCratesAPI Developer Documentation
[![](https://jitpack.io/v/Furnesse/MaskedCratesAPI.svg)](https://jitpack.io/#Furnesse/MaskedCratesAPI) <---- Latest Version

## Introduction

Welcome to the developer documentation for the MaskedCratesAPI! This documentation will guide you on how to integrate your plugin with the MaskedCratesAPI to extend the functionality of MaskedCrates in your own plugin.

## Getting Started

To get started, you'll need the following:

- Basic knowledge of Java programming and Maven.
- Access to the MaskedCratesAPI library as a dependency in your project.

### Adding MaskedCratesAPI as a Dependency

To add MaskedCratesAPI as a dependency in your project, you can use JitPack repository.

Maven:

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.Furnesse</groupId>
    <artifactId>MaskedCratesAPI</artifactId>
    <version>{VERSION}</version>
    <scope>provided</scope>
</dependency>
```

Gradle:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Furnesse:MaskedCratesAPI:{VERSION}'
}
```

Replace `{VERSION}` with the specific release tag or commit hash of MaskedCratesAPI you want to use.

## Integration Steps

Follow these steps to integrate your plugin with the MaskedCratesAPI:

### Step 1: Create your CrateAnimation or CrateEffect
**CrateAnimation**
```java
public class ExampleAnimation extends CrateAnimation {
    public ExampleAnimation(JavaPlugin plugin, String id, int maxTicks, boolean rewardPreview, boolean forceLookAt) {
        super(plugin, id, maxTicks, rewardPreview, forceLookAt);
    }

    @Override
    protected void init(ActiveCrate activeCrate) {
        // This step is crucial as this initializes the armorstand
        // that are being animated

        Player player = activeCrate.getPlayer();

        // Spawns the hologram 3 blocks in front of the player and player's eye level
        Location holoLoc = player.getEyeLocation().add(player.getLocation().getDirection().multiply(3).setY(-1.5));
        // Makes the hologram face the player
        holoLoc = holoLoc.setDirection(player.getLocation().subtract(holoLoc).toVector());

        // Spawns the hologram aka the armorstand that will be manipulated in the animate method
        activeCrate.spawn(holoLoc);
    }

    @Override
    protected void actionOnEnd(Player player, ArmorStand stand, int tick) {
        // Action that will happend at the end of the animation
        // - could be a sound or an effect, what ever you please
        // - it could also just be nothing, as the reward may do
        // what you want,
    }

    @Override
    public void animate(Player player, ArmorStand stand, int tick) {
        // This method will get called every tick of the animation
        // the 'tick' argument is the current tick.
    }
```

**CrateEffect**
```java
public class ExampleEffect extends CrateEffect {
    public ExampleEffect(Plugin plugin, String id) {
        super(plugin, id);
    }

    // This particular effect creates a particle sphere
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
```

### Step 1: Implement MaskedCratesExtension Interface

Your plugin needs to implement the `MaskedCratesExtension` interface provided by the MaskedCratesAPI. This interface defines the contract that your plugin needs to fulfill to extend the functionality of MaskedCrates.

```java
import com.furnesse.maskedcrates.api.MaskedCratesExtension;

public class MyPlugin implements MaskedCratesExtension {
    @Override
    public void registerEffects() {
        new ExampleEffect(plugin, "example");
    }

    @Override
    public void registerAnimations() {
        // 100 meaning the animation will last for 5 seconds 20 ticks = 1 second
        new ExampleAnimation(plugin, "example", 100, true, true);
    }
}
```

### Step 2: Register Your Plugin

Once you've implemented the `MaskedCratesExtension` interface, register your plugin with the MaskedCratesAPI during your plugin's initialization.

```java
import com.furnesse.maskedcrates.api.MaskedCratesAPI;

public class MyPluginMain {

    public void onEnable() {
        // Obtain the MaskedCratesAPI instance (Assuming you have access to it)
        MaskedCratesAPI maskedCratesAPI = JavaPlugin.getPlugin(MaskedCrates.class).getMaskedCratesAPI();

        // Register your plugin
        maskedCratesAPI.registerPlugin(new MyPlugin());
    }
}
```

### Step 3: Depend on the Plugin

Before implementing the plugin functionality, make sure your plugin depends on the MaskedCrates, inside your plugin.yml, `depend: [..., MaskedCrates]`, that way you plugin will load after the MaskedCrates has loaded, so the api can be hook into successfully.

### Step 4: Implement PLugin Functionality

Now that your plugin has successfully registered, you can implement its functionality inside the registerEffects() and registerAnimations() methods from the MaskedCratesExtension interface. These methods will be called when the MaskedCratesAPI initializes.

By following these steps, you can ensure your plugin is properly integrated with the MaskedCrates system and that its functionality is executed as intended.

## Conclusion

By following these integration steps, your plugin will be seamlessly integrated with the MaskedCratesAPI, allowing it to extend the functionality of MaskedCrates and provide a smooth user experience for your users.

---

# Available methods from MaskedCratesAPI

Gets the number of times the specified player has opened the given crate.

```java
int getCratesOpened(Player player, Crate crate);
```

Gets the `Crate` object corresponding to the specified crate name.

```java
Crate getCrate(String crateName);
```

Gets the `Crate` object associated with the given `ItemStack` if it represents a crate.

```java
Crate getCrate(ItemStack itemStack);
```

Opens the specified crate for the given player.

```java
void openCrate(Player player, Crate crate);
```
---

# Using Events from MaskedCratesAPI

In addition to providing extension points for custom functionality, MaskedCratesAPI also offers several events that your plugin can listen to and respond accordingly. These events allow you to hook into various points of the crate opening process and perform actions based on the outcome. This guide will walk you through how to use the following events:

- `OpenCrateEvent`: This event is fired when a player attempts to open a crate.
- `CrateOpenedEvent`: This event is triggered after a crate has been successfully opened by a player.
- `AnimationCancelledEvent`: This event is fired when the crate opening animation is cancelled for any reason.

## Registering Event Listeners

To listen to the events provided by the MaskedCratesAPI, you'll need to create a new class that implements the appropriate event listener interface from Bukkit or your preferred event handling framework.

Here's an example of registering event listeners for the events:

```java
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MyCrateEventListener implements Listener {

    @EventHandler
    public void onOpenCrate(OpenCrateEvent event) {
        // Your event handling logic for OpenCrateEvent
    }

    @EventHandler
    public void onCrateOpened(CrateOpenedEvent event) {
        // Your event handling logic for CrateOpenedEvent
    }

    @EventHandler
    public void onAnimationCancelled(AnimationCancelledEvent event) {
        // Your event handling logic for AnimationCancelledEvent
    }
}
```

Remember to register your event listener class in your plugin's `onEnable()` method:

```java
public class MyPluginMain {

    public void onEnable() {
        // Register the event listener
        Bukkit.getPluginManager().registerEvents(new MyCrateEventListener(), this);
    }
}
```

## Event Details

Now, let's dive into the details of each event and how you can use them in your plugin.

### OpenCrateEvent

This event is fired when a player attempts to open a crate.

```java
import com.furnesse.maskedcrates.api.events.OpenCrateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MyCrateEventListener implements Listener {

    @EventHandler
    public void onOpenCrate(OpenCrateEvent event) {
        Player player = event.getPlayer(); // Get the player who is opening the crate
        // Your event handling logic here
    }
}
```

### CrateOpenedEvent

This event is triggered after a crate has been successfully opened by a player.

```java
import com.furnesse.maskedcrates.api.events.CrateOpenedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MyCrateEventListener implements Listener {

    @EventHandler
    public void onCrateOpened(CrateOpenedEvent event) {
        Player player = event.getPlayer(); // Get the player who opened the crate
        String crateName = event.getCrateName(); // Get the name of the crate that was opened
        // Your event handling logic here
    }
}
```

### AnimationCancelledEvent

This event is fired when the crate opening animation is cancelled for any reason.

```java
import com.furnesse.maskedcrates.api.events.AnimationCancelledEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MyCrateEventListener implements Listener {

    @EventHandler
    public void onAnimationCancelled(AnimationCancelledEvent event) {
        Player player = event.getPlayer(); // Get the player whose animation was cancelled
        String crateName = event.getCrateName(); // Get the name of the crate with the cancelled animation
        // Your event handling logic here
    }
}
```

If you have any questions or need further assistance, don't hesitate to contact us at https://discord.gg/Jcm6u6w. Happy coding!

---
