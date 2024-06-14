package sypztep.arctechz.client.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import sypztep.arctechz.Arctechz;
import sypztep.arctechz.ModConfig;

public record EnforceConfigMatchPayload(boolean flag) implements CustomPayload {
    public static final Id<EnforceConfigMatchPayload> ID = CustomPayload.id("enforce_config_match");
    public static final PacketCodec<PacketByteBuf, EnforceConfigMatchPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, EnforceConfigMatchPayload::flag, EnforceConfigMatchPayload::new);

    private static final Text DISCONNECT_TEXT = Text.literal("The server you are attempting to connect to has ")
            .append(Text.literal("Arctechz").formatted(Formatting.GREEN))
            .append("Your config is missmatch to server.\n\n")
            .append(Text.literal("Please make sure your configuration file matches the server's.\n").formatted(Formatting.RED))
            .append(Text.literal("Your configuration is featureMerge : ").append(ModConfig.featureMerge + "\n").formatted(Formatting.RED));
    public static Text getServerConfig(boolean flag) {
        return Text.literal("Your configuration is featureMerge : ").append(String.valueOf(flag)).formatted();
    }
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void send(ServerPlayerEntity player, boolean flag) {
        ServerPlayNetworking.send(player, new EnforceConfigMatchPayload(flag));
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<EnforceConfigMatchPayload> {
        @Override
        public void receive(EnforceConfigMatchPayload payload, ClientPlayNetworking.Context context) {
            if (ModConfig.featureMerge != payload.flag()) {
                context.player().networkHandler.getConnection().disconnect(DISCONNECT_TEXT.copy().append(getServerConfig(payload.flag())));
            }
        }
    }
}
