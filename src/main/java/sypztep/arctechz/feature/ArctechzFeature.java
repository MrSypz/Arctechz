package sypztep.arctechz.feature;

import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import sypztep.arctechz.feature.data.OverheadData;
import sypztep.arctechz.feature.data.PlayerCosmeticData;
import sypztep.arctechz.feature.render.feature.OverheadFeatureRenderer;
import sypztep.arctechz.feature.render.model.hat.BigHaloModel;
import sypztep.arctechz.feature.render.model.hat.FurinaHatModel;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class ArctechzFeature implements ClientModInitializer {
    public static final String MODID = "arctechz_feature";
    public static Identifier id (String path) {
        return new Identifier(MODID,path);
    }
    static final Type COSMETIC_SELECT_TYPE = new TypeToken<Map<UUID, PlayerCosmeticData>>() {
    }.getType();
    public static final Gson COSMETICS_GSON = new GsonBuilder().registerTypeAdapter(PlayerCosmeticData.class, new PlayerCosmeticDataParser()).create();

    // feature cosmetics
    private static final String COSMETICS_URL = "https://mrsypz.github.io/sypztep.github.io/uuidfeature.json";
    public static ImmutableMap<String, OverheadData> OVERHEADS_DATA;
    private static final Map<UUID, PlayerCosmeticData> PLAYER_COSMETICS = Collections.emptyMap();

    public static @Nullable PlayerCosmeticData getCosmeticData(PlayerEntity player) {
        return PLAYER_COSMETICS.get(player.getUuid());
    }
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(BigHaloModel.MODEL_LAYER, BigHaloModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(FurinaHatModel.MODEL_LAYER, FurinaHatModel::getTexturedModelData);

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER) {
                @SuppressWarnings("unchecked") var playerRenderer = (FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>) entityRenderer;
                registrationHelper.register(new OverheadFeatureRenderer(playerRenderer, context));
            }
        });

        OVERHEADS_DATA = ImmutableMap.<String, OverheadData>builder()
                .put("hanega_halo", new OverheadData(BigHaloModel::new, "hanega_halo"))
                .put("redsight", new OverheadData(BigHaloModel::new, "redsight"))
                .put("furina_hat", new OverheadData(FurinaHatModel::new, "furina_hat"))
                .build();

    }
    private static class PlayerCosmeticDataParser implements JsonDeserializer<PlayerCosmeticData> {
        @Override
        public PlayerCosmeticData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            return new PlayerCosmeticData (jsonObject.get("overhead"));
        }
    }
}
