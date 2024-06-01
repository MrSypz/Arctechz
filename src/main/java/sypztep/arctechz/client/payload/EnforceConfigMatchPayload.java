package sypztep.arctechz.client.payload;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public record EnforceConfigMatchPayload(int encoding) implements CustomPayload {
    public static final Id<EnforceConfigMatchPayload> ID = CustomPayload.id(Arctechz.id("enforce_config_match").toString());
    public static final PacketCodec<PacketByteBuf, EnforceConfigMatchPayload> CODEC = PacketCodec.tuple(PacketCodecs.VAR_INT, EnforceConfigMatchPayload::encoding, EnforceConfigMatchPayload::new);

    private static final Text DISCONNECT_TEXT = Text.literal("The server you are attempting to connect to has ")
            .append(Text.literal("Arctechz").formatted(Formatting.GREEN))
            .append("Your config is missmatch to server.\n\n")
            .append(Text.literal("Please make sure your configuration file matches the server's.\n").formatted(Formatting.RED))
            .append(Text.literal("Your configuration is featureMerge : ").append(ModConfig.featureMerge + "\n").formatted(Formatting.RED))
            .append(Text.literal("Server configuration is featureMerge : ").append(ServerConfig() + "\n").formatted(Formatting.GREEN))
            .append(Text.literal("Go to mod config and turn into : " + ServerConfig()).append(ServerConfig() + "\n").formatted(Formatting.GRAY));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void send(ServerPlayerEntity player, int encoding) {
        ServerPlayNetworking.send(player, new EnforceConfigMatchPayload(encoding));
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<EnforceConfigMatchPayload> {
        @Override
        public void receive(EnforceConfigMatchPayload payload, ClientPlayNetworking.Context context) {
            if (ModConfig.encode() != payload.encoding()) {
                context.player().networkHandler.getConnection().disconnect(DISCONNECT_TEXT);
            }
        }
    }

    public static boolean ServerConfig() {
        String jsonFilePath = "path/to/your/config.json";
        boolean featureMerge = false;
        try (Reader reader = new FileReader(jsonFilePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            featureMerge = jsonObject.getAsJsonPrimitive("featureMerge").getAsBoolean();
            return featureMerge;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return featureMerge;
    }
}
