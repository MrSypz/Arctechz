package sypztep.arctechz.feature.render.model.hat;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import sypztep.arctechz.feature.ArctechzFeature;

public class TingyunEarsModel extends OverheadModel {
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(ArctechzFeature.id("tingyun_ears"), "main");
	public TingyunEarsModel(EntityRendererFactory.Context ctx) {
		super(ctx, MODEL_LAYER);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData ears = Head.addChild("ears", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.5F, -4.75F, -0.5F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 8).mirrored().cuboid(-1.5F, -6.75F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 6).mirrored().cuboid(-1.5F, -7.75F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.6F, -5.65F, -1.6F, 0.1138F, -0.2585F, 0.3948F));

		ModelPartData ears2 = Head.addChild("ears2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -4.75F, -0.5F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 8).cuboid(-0.5F, -6.75F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 6).cuboid(0.5F, -7.75F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.6F, -5.65F, -1.6F, 0.1138F, 0.2585F, -0.3948F));
		return TexturedModelData.of(modelData, 128, 128);
	}
}