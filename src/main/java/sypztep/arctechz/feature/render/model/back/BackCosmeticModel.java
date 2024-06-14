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
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		this.back.render(matrices, vertices, light, overlay);
	}
}
