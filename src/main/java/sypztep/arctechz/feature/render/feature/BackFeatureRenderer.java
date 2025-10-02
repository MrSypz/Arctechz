package sypztep.arctechz.feature.render.feature;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import sypztep.arctechz.ModConfig;
import sypztep.arctechz.feature.ArctechzFeature;
import sypztep.arctechz.feature.data.PlayerCosmeticData;
import sypztep.arctechz.feature.render.model.back.BackCosmeticModel;
import sypztep.arctechz.feature.render.model.back.TingyunTailCapeModel;

import java.util.Map;
import java.util.stream.Collectors;

public class BackFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private final Map<String, ResolveBackData> models;

    public BackFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> featureRendererContext, EntityRendererFactory.Context loader) {
        super(featureRendererContext);
        this.models = ArctechzFeature.BACKCOSMETIC_DATA.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, data -> new ResolveBackData(data.getValue().getTexture(), data.getValue().createModel(loader))));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        PlayerCosmeticData cosmeticData = ArctechzFeature.getCosmeticData(entity);
//        if (ModConfig.shouldDisplayCosmetics() && cosmeticData != null && !entity.isInvisible()) {
            String playerBackData = "tingyun_tail";
            if (playerBackData != null) {
                ResolveBackData resolveBackData = this.models.get(playerBackData);
                if (resolveBackData != null) {

                    Identifier texture = resolveBackData.texture();
                    BackCosmeticModel model = resolveBackData.model();

                    if (model instanceof WiggleFeature wiggleModel) {
                        wiggleModel.applyTailPhysics(entity, tickDelta); // Changed method name

                        float currentWiggleAmount = getCurrentWiggleAmount(entity, tickDelta);
                        float q = 0;
                        float t = MathHelper.lerp(tickDelta, entity.prevStrideDistance, entity.strideDistance);
                        q += MathHelper.sin(MathHelper.lerp(tickDelta, entity.prevHorizontalSpeed, entity.horizontalSpeed) * 2.0f) * 3.0f * t;

                        matrices.push();
                        model.back.pivotX = this.getContextModel().body.pivotX;
                        model.back.pivotY = this.getContextModel().body.pivotY;
                        model.back.pitch = this.getContextModel().body.pitch + Math.abs(q);
                        model.back.yaw = this.getContextModel().body.yaw + currentWiggleAmount;

                        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(texture));
                        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

                        matrices.pop();
                    } else {
                        model.back.pivotX = this.getContextModel().body.pivotX;
                        model.back.pivotY = this.getContextModel().body.pivotY;
                        model.back.pitch = this.getContextModel().body.pitch;
                        model.back.yaw = this.getContextModel().body.yaw;
                        model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(texture)), light, OverlayTexture.DEFAULT_UV);
                    }
                }
//            }
        }
    }

    private static float getCurrentWiggleAmount(AbstractClientPlayerEntity entity, float tickdelta) {
        float previousWiggleAmount = 0.0f;
        float time = (entity.age + tickdelta); // รวม tick + partial tick

        float wiggleIntensity = 1.5f + Math.min(entity.getHealth() * 0.1f, 2);
        float wiggleSpeed = 0.1f;
        float targetWiggleAmount = MathHelper.sin(time * wiggleSpeed) * wiggleIntensity;

        float lerpSpeed = 0.05f;
        return MathHelper.lerp(lerpSpeed, previousWiggleAmount, targetWiggleAmount);
    }

    private record ResolveBackData(Identifier texture, BackCosmeticModel model) {
    }
}
