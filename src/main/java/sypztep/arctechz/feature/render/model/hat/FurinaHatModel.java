// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package sypztep.arctechz.feature.render.model.hat;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import sypztep.arctechz.feature.ArctechzFeature;

public class FurinaHatModel extends OverheadModel {
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(ArctechzFeature.id("furina_hat_model"), "main");

	public FurinaHatModel(EntityRendererFactory.Context ctx) {
		super(ctx, MODEL_LAYER);
        this.head.getChild("furina_hat_model");
    }
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 7).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-4.0f)), ModelTransform.pivot(0,0,0));
		ModelPartData hat = head.addChild("furina_hat_model", ModelPartBuilder.create().uv(29, 25).cuboid(-3.0F, 0.3415F, -4.0F, 6.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(27, 43).cuboid(-3.0F, -4.6585F, -3.0F, 6.0F, 5.0F, 6.0F, new Dilation(0.0F))
		.uv(32, 2).cuboid(-3.0F, -5.6585F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.1F))
		.uv(0, 23).cuboid(-3.0F, -4.0585F, -3.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.2F))
		.uv(0, 16).cuboid(-3.2F, -1.6585F, -3.2F, 6.4F, 1.0F, 6.4F, new Dilation(-0.1F))
		.uv(37, 11).cuboid(-3.0F, -5.6585F, -0.5F, 6.0F, 2.0F, 1.0F, new Dilation(0.1F)), ModelTransform.of(2.6F, -7.6415F, 2.0F, -0.6056F, 1.1962F, -0.1852F));

        hat.addChild("cube_r1", ModelPartBuilder.create().uv(37, 11).cuboid(-3.0F, -1.0F, -0.5F, 6.0F, 2.0F, 1.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, -4.6585F, 0.0F, 0.0F, 1.5708F, 0.0F));

        hat.addChild("cube_r2", ModelPartBuilder.create().uv(16, 24).mirrored().cuboid(0.0F, -0.5F, -4.0F, 2.0F, 1.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.75F, 0.9085F, 0.0F, 0.0F, 0.0F, -0.5236F));

        hat.addChild("cube_r3", ModelPartBuilder.create().uv(16, 24).cuboid(-2.0F, -0.5F, -4.0F, 2.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-2.75F, 0.9085F, 0.0F, 0.0F, 0.0F, 0.5236F));

        hat.addChild("cube_r4", ModelPartBuilder.create().uv(0, 30).cuboid(-0.3132F, -0.0701F, -0.8854F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.5347F, 0.4637F, 3.3643F, 0.601F, 0.5481F, 0.485F));

        ModelPartData ribbon = hat.addChild("ribbon", ModelPartBuilder.create().uv(0, 46).cuboid(-0.5F, -0.5F, -0.1F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0585F, 3.0F, 0.3054F, 0.0F, 0.0F));

        ribbon.addChild("cube_r5", ModelPartBuilder.create().uv(12, 50).cuboid(0.3363F, -0.3911F, -0.1248F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0343F, -2.0087F, -5.6819F, -0.3522F, -0.0483F, -0.2442F));

        ribbon.addChild("cube_r6", ModelPartBuilder.create().uv(12, 50).cuboid(0.3363F, -0.3911F, -0.1248F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.0153F, -2.0401F, -5.68F, -0.341F, 0.102F, 0.166F));

        ribbon.addChild("cube_r7", ModelPartBuilder.create().uv(12, 49).cuboid(0.3363F, -0.3911F, -0.1248F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.0153F, -2.1401F, -5.88F, -2.9838F, 0.1686F, 2.9442F));

        ribbon.addChild("cube_r8", ModelPartBuilder.create().uv(12, 49).cuboid(0.3363F, -0.3911F, -0.1248F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0343F, -2.1087F, -5.8819F, -2.9279F, 0.0867F, -2.9067F));

        ribbon.addChild("cube_r9", ModelPartBuilder.create().uv(0, 46).cuboid(-0.5343F, -0.1413F, -0.8181F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0343F, -2.3587F, -5.8819F, -2.8362F, 0.0F, 3.1416F));

        ribbon.addChild("cube_r10", ModelPartBuilder.create().uv(12, 51).cuboid(0.5F, -0.5F, 0.5F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0229F, -0.1289F, -0.4349F));

        ribbon.addChild("cube_r11", ModelPartBuilder.create().uv(12, 46).cuboid(-1.5F, -2.6804F, -0.0278F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.3F, 0.4F, -0.2618F, 0.0F, 0.0F));

        ribbon.addChild("cube_r12", ModelPartBuilder.create().uv(12, 51).mirrored().cuboid(-2.5F, -0.5F, 0.5F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.1F, 0.0F, 0.0F, -0.0229F, 0.1289F, 0.4349F));

        ribbon.addChild("cube_r13", ModelPartBuilder.create().uv(0, 48).cuboid(0.5F, -0.85F, 0.5F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1354F, -0.1891F, 0.4999F));

        ribbon.addChild("cube_r14", ModelPartBuilder.create().uv(0, 48).mirrored().cuboid(-2.5F, -1.0F, 0.5F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1354F, 0.1891F, -0.4999F));

        ModelPartData bone2 = head.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, -8.0F, 0.0F));

        bone2.addChild("cube_r15", ModelPartBuilder.create().uv(25, 16).cuboid(-6.9F, -6.5F, 0.0F, 9.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, -1.8F, -0.3989F, -0.4912F, 0.366F));
        return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		head.render(matrices, vertices, light, overlay, color);
	}
}