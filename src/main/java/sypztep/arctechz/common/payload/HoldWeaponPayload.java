package sypztep.arctechz.common.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import sypztep.arctechz.Arctechz;
import sypztep.arctechz.common.component.entity.BackWeaponComponent;

public record HoldWeaponPayload(boolean holdweapon) implements CustomPayload {
    public static final CustomPayload.Id<HoldWeaponPayload> ID = CustomPayload.id("hold");
    public static final PacketCodec<PacketByteBuf, HoldWeaponPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, HoldWeaponPayload::holdweapon, HoldWeaponPayload::new);
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
    public static void send(boolean holdweapon) {
        ClientPlayNetworking.send(new HoldWeaponPayload(holdweapon));
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<HoldWeaponPayload> {
        @Override
        public void receive(HoldWeaponPayload payload, ServerPlayNetworking.Context context) {
            BackWeaponComponent.setHoldingBackWeapon(context.player(), payload.holdweapon());
        }
    }
}
