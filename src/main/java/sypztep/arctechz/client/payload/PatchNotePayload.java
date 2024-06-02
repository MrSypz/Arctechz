package sypztep.arctechz.client.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import sypztep.arctechz.Arctechz;
import sypztep.arctechz.client.screen.PatchNoteScreen;

public record PatchNotePayload() implements CustomPayload {
    public static final CustomPayload.Id<PatchNotePayload> ID = CustomPayload.id(Arctechz.id("patchnote").toString());
    public static final PacketCodec<PacketByteBuf, PatchNotePayload> CODEC = PacketCodec.unit(new PatchNotePayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
    public static void send(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new PatchNotePayload());
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<PatchNotePayload> {
        @Override
        public void receive(PatchNotePayload payload, ClientPlayNetworking.Context context) {
            context.client().setScreen(new PatchNoteScreen());
        }
    }
}
