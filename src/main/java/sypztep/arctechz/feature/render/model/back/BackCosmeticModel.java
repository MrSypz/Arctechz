package sypztep.arctechz.feature.render.model.back;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import sypztep.arctechz.feature.render.renderer.GlowyRenderLayer;

public abstract class BackCosmeticModel extends Model {
	public final ModelPart back;
	public BackCosmeticModel(EntityRendererFactory.Context ctx, EntityModelLayer entityModelLayer) {
		super(GlowyRenderLayer::get);
		this.back = ctx.getPart(entityModelLayer).getChild("body");
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.back.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}
