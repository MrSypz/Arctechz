package sypztep.arctechz;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    @Entry
    public static boolean featureMerge = false;
    @Entry
    public static int stackSize = 2048;
    @Entry(category = "client", min = 1)
    public static int viewItemDistance = 512;
    @Entry
    public static float mergeRange = 1.5F;
    @Entry
    public static CosmeticsOptions cosmetics = CosmeticsOptions.ENABLE;

    public static boolean shouldDisplayCosmetics() {
        return cosmetics == CosmeticsOptions.ENABLE || cosmetics == CosmeticsOptions.FIRST_PERSON;
    }

    public static int encode() {
        String encoding = "" + featureMerge;
        return encoding.hashCode();
    }

    public enum CosmeticsOptions {
        ENABLE, FIRST_PERSON, DISABLE
    }
}
