package sypztep.arctechz.feature.render.model.hat;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Vector3f;
import sypztep.arctechz.feature.ArctechzFeature;

public class BigHaloModel extends OverheadModel {
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(ArctechzFeature.id("big_halo"), "main");

	public BigHaloModel(EntityRendererFactory.Context ctx) {
		super(ctx, MODEL_LAYER);

		ModelPart bigHalo = this.head.getChild("big_halo");
		Vector3f vector3f = new Vector3f(-0.25f);
		bigHalo.scale(vector3f);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData modelPartData1 = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 7).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-4.0f)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData1.addChild("big_halo",  ModelPartBuilder.create().uv(0, 0).cuboid(-32.0F, -32.0F, 0.0F, 64.0F, 64.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0F, -4.0F, 4.0F));
		return TexturedModelData.of(modelData, 128, 64);
	}
}
