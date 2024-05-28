package sypztep.arctechz;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    @Entry
    public static int stackSize = 2048;
    @Entry(category = "client",min = 1)
    public static int viewItemDistance = 512;
}
