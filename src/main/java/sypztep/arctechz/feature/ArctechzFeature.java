package sypztep.arctechz.feature;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import sypztep.arctechz.feature.data.BackData;
import sypztep.arctechz.feature.data.OverheadData;
import sypztep.arctechz.feature.data.PlayerCosmeticData;
import sypztep.arctechz.feature.init.LoadData;
import sypztep.arctechz.feature.render.feature.BackFeatureRenderer;
import sypztep.arctechz.feature.render.feature.OverheadFeatureRenderer;
import sypztep.arctechz.feature.render.model.hat.BigHaloModel;
import sypztep.arctechz.feature.render.model.hat.FurinaHatModel;
import sypztep.arctechz.feature.render.model.hat.HutaoHat;


public class ArctechzFeature implements ClientModInitializer {
    public static final String MODID = "arctechz_feature";
    public static Identifier id (String path) {
        return Identifier.of(MODID,path);
    }
    public static ImmutableMap<String, OverheadData> OVERHEADS_DATA;
    public static ImmutableMap<String, BackData> BACKCOSMETIC_DATA;

    public static @Nullable PlayerCosmeticData getCosmeticData(PlayerEntity player) {
        return LoadData.PLAYER_COSMETICS.get(player.getUuid());
    }
    @Override
    public void onInitializeClient() {
        LoadData.loadPlayerCosmetics();
        //OverHead Model
        EntityModelLayerRegistry.registerModelLayer(BigHaloModel.MODEL_LAYER, BigHaloModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(FurinaHatModel.MODEL_LAYER, FurinaHatModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HutaoHat.MODEL_LAYER, HutaoHat::getTexturedModelData);

        //BackItem Model


        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER) {
                @SuppressWarnings("unchecked") var playerRenderer = (FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>) entityRenderer;
                registrationHelper.register(new OverheadFeatureRenderer(playerRenderer, context));
                registrationHelper.register(new BackFeatureRenderer(playerRenderer, context));
            }
        });
        //OverHead
        OVERHEADS_DATA = ImmutableMap.<String, OverheadData>builder()
                .put("hanega_big_halo", new OverheadData(BigHaloModel::new, "hanega_big_halo"))
                .put("redsight_big_halo", new OverheadData(BigHaloModel::new, "redsight_big_halo"))
                .put("wnell_big_halo", new OverheadData(BigHaloModel::new, "wnell_big_halo"))
                .put("furina_hat", new OverheadData(FurinaHatModel::new, "furina_hat"))
                .put("hutao_hat", new OverheadData(HutaoHat::new, "hutao_hat"))
                .build();
        //BackItem
        BACKCOSMETIC_DATA = ImmutableMap.<String, BackData>builder()
                .build();

    }

}
