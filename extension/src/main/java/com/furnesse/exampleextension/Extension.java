package com.furnesse.exampleextension;

import com.furnesse.api.MaskedCratesAPI;
import com.furnesse.api.MaskedCratesServiceLocator;
import org.bukkit.plugin.java.JavaPlugin;

public class Extension extends JavaPlugin {
    private MaskedCratesAPI maskedCratesApi;

    @Override
    public void onEnable() {
        maskedCratesApi = MaskedCratesServiceLocator.getMaskedCratesAPI();
        maskedCratesApi.registerPlugin(new MaskedExtension(this));

        System.out.println("Is the Vote crate registered?" + (maskedCratesApi.getCrate("Vote") != null));
        System.out.println("Is the Example crate registered?" + (maskedCratesApi.getCrate("example") != null));
        System.out.println("Is the Test crate registered?" + (maskedCratesApi.getCrate("TesT") != null));
    }


    public MaskedCratesAPI getMaskedCratesAPI() {
        return maskedCratesApi;
    }
}
