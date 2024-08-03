package sypztep.arctechz.feature.render.model.hat;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import sypztep.arctechz.feature.ArctechzFeature;

public class FurinaHatOldModel extends OverheadModel {
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(ArctechzFeature.id("furina_hat_old_model"), "main");

	public FurinaHatOldModel(EntityRendererFactory.Context ctx) {
		super(ctx, MODEL_LAYER);

		ModelPart furinaHatModel = this.head.getChild("furina_hat_old_model");
		furinaHatModel.xScale = 0.688f;
		furinaHatModel.yScale = 0.788f;
		furinaHatModel.zScale = 0.688f;
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData modelPartData1 = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 7).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-4.0f)), ModelTransform.pivot(0,0,0));
		ModelPartData furina_hat_model = modelPartData1.addChild("furina_hat_old_model", ModelPartBuilder.create().uv(31, 31).cuboid(-5.5F, -11.5F, -5.5F, 11.0F, 5.0F, 11.0F, new Dilation(0.0F))
				.uv(53, 36).cuboid(5.5F, -18.5F, -5.5F, 0.0F, 7.0F, 11.0F, new Dilation(0.0F))
				.uv(53, 36).cuboid(-5.5F, -18.5F, -5.5F, 0.0F, 7.0F, 11.0F, new Dilation(0.0F))
				.uv(53, 47).cuboid(-5.5F, -18.5F, 5.5F, 11.0F, 7.0F, 0.0F, new Dilation(0.0F))
				.uv(53, 47).cuboid(-5.5F, -18.5F, -5.5F, 11.0F, 7.0F, 0.0F, new Dilation(0.0F))
				.uv(31, 39).cuboid(-4.5F, -11.5F, 3.5F, 9.0F, 5.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 55).cuboid(-5.0F, -15.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F))
				.uv(0, 59).cuboid(-8.0F, -5.0F, -8.0F, 15.0F, 0.5F, 15.0F, new Dilation(0.0F))
				.uv(0, 63).cuboid(-7.0F, -7.0F, -7.0F, 10.0F, 2.0F, 10.0F, new Dilation(0.0F))
				.uv(2, 28).cuboid(-0.55F, -5.0985F, 8.9F, 0.0F, 11.0F, 3.0F, new Dilation(0.0F))
				.uv(20, 49).cuboid(-3.1F, -11.5F, 8.1F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.45f,-3.0f,-2.8f,-0.57f,-70f,0.9f)); //old -0.4f,1.8f,0.25f//pich งัด

        furina_hat_model.addChild("furina_hat_model_r1", ModelPartBuilder.create().uv(2, 42).cuboid(-2.0F, -2.0F, 0.5F, 4.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(17, 21).cuboid(-2.0F, -2.0F, 2.5F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(17, 19).cuboid(1.0F, -2.0F, 0.5F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -9.5F, 5.5F, 0.0F, 0.0F, -0.3927F));

        furina_hat_model.addChild("furina_hat_model_r2", ModelPartBuilder.create().uv(17, 19).cuboid(-0.2365F, -1.02F, -1.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.5335F, -6.564F, 6.95F, 0.0F, 0.0F, -0.7854F));

        furina_hat_model.addChild("furina_hat_model_r3", ModelPartBuilder.create().uv(17, 19).cuboid(-1.0F, -4.4961F, -2.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(2, 42).cuboid(-2.0F, -4.4961F, -2.0F, 4.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(17, 21).cuboid(-1.0F, -4.4961F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.5433F, -7.2039F, 8.0F, 0.0F, 0.0F, 0.3927F));

        furina_hat_model.addChild("furina_hat_model_r4", ModelPartBuilder.create().uv(17, 21).cuboid(-5.5F, -0.5F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.2331F, -4.9956F, 7.95F, 0.0F, 0.0F, 0.7854F));

        furina_hat_model.addChild("furina_hat_model_r5", ModelPartBuilder.create().uv(17, 19).cuboid(1.5F, 5.5F, -2.05F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(17, 21).cuboid(-1.5F, 5.5F, -0.05F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.89F, -12.0667F, 8.0F, 0.0F, 0.0F, 0.7854F));

        furina_hat_model.addChild("furina_hat_model_r6", ModelPartBuilder.create().uv(2, 19).cuboid(0.7478F, 0.0654F, -1.5F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-2.0478F, -7.2654F, 8.3F, 0.0F, 0.0F, -0.3927F));

        furina_hat_model.addChild("furina_hat_model_r7", ModelPartBuilder.create().uv(2, 23).cuboid(4.2594F, -9.5F, 2.5F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-4.7594F, 1.8942F, 8.3F, 0.3927F, 0.0F, 0.0F));

        furina_hat_model.addChild("furina_hat_model_r8", ModelPartBuilder.create().uv(2, 19).cuboid(0.0F, -1.0F, -1.7F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.2F, -6.5F, 8.5F, 0.0F, 0.0F, 0.3927F));

        furina_hat_model.addChild("ahoge_model_r1", ModelPartBuilder.create().uv(2, 45).cuboid(-4.5F, -3.5F, -7.0F, 9.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(12.0F, -7.0F, -5.5F, 0.0F, 0.7854F, 0.0F));
        return TexturedModelData.of(modelData, 75, 75);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		head.render(matrices, vertices, light, overlay, color);
	}
}