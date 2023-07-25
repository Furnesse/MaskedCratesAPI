package com.furnesse.api;

public class MaskedCratesServiceLocator {
    private static MaskedCratesAPI maskedCratesAPI;

    /**
     * @return The MaskedCratesAPI instance
     */
    public static MaskedCratesAPI getMaskedCratesAPI() {
        return maskedCratesAPI;
    }

    public static void setMaskedCratesAPI(MaskedCratesAPI maskedCratesAPI) {
        MaskedCratesServiceLocator.maskedCratesAPI = maskedCratesAPI;
    }
}
