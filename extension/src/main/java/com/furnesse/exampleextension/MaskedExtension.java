package com.furnesse.exampleextension;

import com.furnesse.api.MaskedCratesExtension;
import com.furnesse.exampleextension.animations.ExampleAnimation;

public class MaskedExtension implements MaskedCratesExtension {
    private final Extension plugin;

    public MaskedExtension(Extension plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerEffects() {
//        effects.add(new ExampleEffect());
    }

    @Override
    public void registerAnimations() {
        plugin.getMaskedCratesAPI().registerAnimation(new ExampleAnimation(plugin, "example", 100, true, true));
    }

}
