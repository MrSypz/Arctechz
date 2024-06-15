package sypztep.arctechz;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    @Entry
    public static CosmeticsOptions cosmetics = CosmeticsOptions.ENABLE;

    public static boolean shouldDisplayCosmetics() {
        return cosmetics == CosmeticsOptions.ENABLE || cosmetics == CosmeticsOptions.FIRST_PERSON;
    }
    public enum CosmeticsOptions {
        ENABLE, FIRST_PERSON, DISABLE
    }
}
