package sypztep.arctechz.feature.init;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import net.minecraft.client.MinecraftClient;
import sypztep.arctechz.feature.data.PlayerCosmeticData;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


public class LoadData {
    private static final String COSMETICS_URL = "https://mrsypz.github.io/uuidfeature.json";
    private static final Type COSMETIC_SELECT_TYPE = new TypeToken<Map<UUID, PlayerCosmeticData>>() {
    }.getType();
    private static final Gson COSMETICS_GSON = new GsonBuilder().registerTypeAdapter(PlayerCosmeticData.class, new PlayerCosmeticDataParser()).create();
    public static Map<UUID, PlayerCosmeticData> PLAYER_COSMETICS = Collections.emptyMap();
    public static void loadPlayerCosmetics() {
        // get feature player cosmetics
        CompletableFuture.supplyAsync(() -> {
            try (Reader reader = new InputStreamReader(new URL(COSMETICS_URL).openStream())) {
                return COSMETICS_GSON.<Map<UUID, PlayerCosmeticData>>fromJson(reader, COSMETIC_SELECT_TYPE);
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            return null;
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        }).thenAcceptAsync(playerData -> {
            if (playerData != null) {
                PLAYER_COSMETICS = playerData;
            } else {
                PLAYER_COSMETICS = Collections.emptyMap();
            }
        }, MinecraftClient.getInstance());
    }
    private static class PlayerCosmeticDataParser implements JsonDeserializer<PlayerCosmeticData> {
        @Override
        public PlayerCosmeticData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            return new PlayerCosmeticData (jsonObject.get("overhead")
                    ,jsonObject.get("back"));
        }
    }
}
