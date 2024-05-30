package sypztep.arctechz.client.payload;


import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import sypztep.arctechz.Arctechz;

public record HandleDropSlotPayload() implements CustomPayload {
    public static final CustomPayload.Id<HandleDropSlotPayload> ID = CustomPayload.id(Arctechz.id("handle_drop_backslot").toString());
    public static final PacketCodec<PacketByteBuf, HandleDropSlotPayload> CODEC = PacketCodec.unit(new HandleDropSlotPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
    public static void send(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new HandleDropSlotPayload());
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<HandleDropSlotPayload> {
        @Override
        public void receive(HandleDropSlotPayload payload, ClientPlayNetworking.Context context) {
            if (context.client().player != null) {
                context.client().player.sendMessage(Text.translatable(Arctechz.id("backslot.feature.dropfail").toString()).formatted(Formatting.GRAY),true);
                context.client().player.playSound(SoundEvents.ENTITY_VILLAGER_NO, 1, 1.0f);
            }
        }
    }
}
