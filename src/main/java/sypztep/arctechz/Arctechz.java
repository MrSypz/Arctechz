package sypztep.arctechz;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import sypztep.arctechz.client.event.SendPatchNoteEvent;
import sypztep.arctechz.client.payload.EnforceConfigMatchPayload;
import sypztep.arctechz.client.payload.HandleDropSlotPayload;
import sypztep.arctechz.common.init.ModEntityTypes;
import sypztep.arctechz.common.init.ModMobSpawnEvent;
import sypztep.arctechz.common.payload.HoldWeaponPayload;
import sypztep.arctechz.common.payload.SwapInvPayload;
import sypztep.arctechz.common.payload.SwapWeaponPayload;

public class Arctechz implements ModInitializer {
    public static final String MODID = "arctechz";

    public static Identifier id(String id) {
        return new Identifier(MODID, id);
    }

    @Override
    public void onInitialize() {
        MidnightConfig.init(MODID, ModConfig.class);
        initPayloads();
        ModEntityTypes.init();
        ModMobSpawnEvent.init();
    }

    private void initPayloads() {
        ServerPlayConnectionEvents.JOIN.register(new SendPatchNoteEvent());

        PayloadTypeRegistry.playS2C().register(HandleDropSlotPayload.ID, HandleDropSlotPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(EnforceConfigMatchPayload.ID, EnforceConfigMatchPayload.CODEC);

        PayloadTypeRegistry.playC2S().register(HoldWeaponPayload.ID, HoldWeaponPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SwapInvPayload.ID, SwapInvPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SwapWeaponPayload.ID, SwapWeaponPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(HoldWeaponPayload.ID, new HoldWeaponPayload.Receiver());
        ServerPlayNetworking.registerGlobalReceiver(SwapInvPayload.ID, new SwapInvPayload.Receiver());
        ServerPlayNetworking.registerGlobalReceiver(SwapWeaponPayload.ID, new SwapWeaponPayload.Receiver());
    }
}