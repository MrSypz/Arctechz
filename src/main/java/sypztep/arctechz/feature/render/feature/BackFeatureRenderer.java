package sypztep.arctechz.feature.render.feature;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import sypztep.arctechz.ModConfig;
import sypztep.arctechz.feature.ArctechzFeature;
import sypztep.arctechz.feature.data.PlayerCosmeticData;
import sypztep.arctechz.feature.render.model.back.BackCosmeticModel;

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
		if (ModConfig.shouldDisplayCosmetics() && cosmeticData != null && !entity.isInvisible()) {
			String playerBackData = cosmeticData.getBack();
			if (playerBackData != null) {
				ResolveBackData resolveBackData = this.models.get(playerBackData);
				if (resolveBackData != null) {
					Identifier texture = resolveBackData.texture();
					BackCosmeticModel model = resolveBackData.model();

					model.back.pivotX = this.getContextModel().body.pivotX;
					model.back.pivotY = this.getContextModel().body.pivotY;
					model.back.pitch = this.getContextModel().body.pitch;
					model.back.yaw = this.getContextModel().body.yaw;

					model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(texture)), light, OverlayTexture.DEFAULT_UV);
				}
			}
		}
	}

	private record ResolveBackData(Identifier texture, BackCosmeticModel model) {
	}
}
