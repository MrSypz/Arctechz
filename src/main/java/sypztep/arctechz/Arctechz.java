package sypztep.arctechz;

import eu.midnightdust.core.config.MidnightConfigExample;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Arctechz implements ModInitializer {
    public static final String MODID = "arctechz";
    public static Identifier id (String id) {
        return new Identifier(MODID,id);
    }
    @Override
    public void onInitialize() {
        MidnightConfig.init("arctechz", ModConfig.class);
    }
}