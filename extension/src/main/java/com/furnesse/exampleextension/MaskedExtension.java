package com.furnesse.exampleextension;

import com.furnesse.api.MaskedCratesExtension;
import com.furnesse.exampleextension.animations.ExampleAnimation;
import com.furnesse.exampleextension.effects.ExampleEffect;

public class MaskedExtension implements MaskedCratesExtension {
    private final Extension plugin;

    public MaskedExtension(Extension plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerEffects() {
        new ExampleEffect(plugin, "example");
    }

    @Override
    public void registerAnimations() {
        new ExampleAnimation(plugin, "example", 100, true, true);
    }

}
