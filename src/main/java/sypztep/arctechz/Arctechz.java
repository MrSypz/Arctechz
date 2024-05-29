package sypztep.arctechz;

import eu.midnightdust.core.config.MidnightConfigExample;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import sypztep.arctechz.common.init.ModCustomPayload;
import sypztep.arctechz.common.payload.HoldWeaponPayload;
import sypztep.arctechz.common.payload.SwapInvPayload;
import sypztep.arctechz.common.payload.SwapWeaponPayload;

public class Arctechz implements ModInitializer {
    public static final String MODID = "arctechz";
    public static Identifier id (String id) {
        return new Identifier(MODID,id);
    }
    @Override
    public void onInitialize() {
        MidnightConfig.init("arctechz", ModConfig.class);
        ModCustomPayload.init();
        initPayloads();
    }
    private void initPayloads() {
        PayloadTypeRegistry.playC2S().register(HoldWeaponPayload.ID, HoldWeaponPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SwapInvPayload.ID, SwapInvPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SwapWeaponPayload.ID, SwapWeaponPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(HoldWeaponPayload.ID, new HoldWeaponPayload.Receiver());
        ServerPlayNetworking.registerGlobalReceiver(SwapInvPayload.ID, new SwapInvPayload.Receiver());
        ServerPlayNetworking.registerGlobalReceiver(SwapWeaponPayload.ID, new SwapWeaponPayload.Receiver());
    }
}