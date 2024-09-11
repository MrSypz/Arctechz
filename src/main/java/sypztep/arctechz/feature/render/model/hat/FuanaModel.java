package sypztep.arctechz.feature.render.model.hat;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import sypztep.arctechz.feature.ArctechzFeature;

public class FuanaModel extends OverheadModel {
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(ArctechzFeature.id("fuana_atler"), "main");

	public FuanaModel(EntityRendererFactory.Context ctx) {
		super(ctx, MODEL_LAYER);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData extra = Head.addChild("extra", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        extra.addChild("cube_r1", ModelPartBuilder.create().uv(10, 3).mirrored().cuboid(-4.0F, -2.5F, 0.0F, 5.0F, 6.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.0F, -27.6259F, 0.1572F, -0.0271F, 0.2617F, -0.007F));

        extra.addChild("cube_r2", ModelPartBuilder.create().uv(0, 3).cuboid(-1.0F, -2.5F, 0.0F, 5.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -27.6259F, 0.1572F, -0.0271F, -0.2617F, 0.007F));

        ModelPartData flowers = extra.addChild("flowers", ModelPartBuilder.create(), ModelTransform.pivot(4.9775F, -28.0486F, -0.0417F));

        flowers.addChild("cube_r3", ModelPartBuilder.create().uv(2, 1).cuboid(-0.1534F, -0.1534F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.6F, 0.4F, 0.6719F, 0.6895F, -2.4008F));

        flowers.addChild("cube_r4", ModelPartBuilder.create().uv(2, 1).cuboid(0.2466F, -0.1534F, 0.325F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.5261F, 0.8565F, 0.6802F, -0.1393F, 0.2262F, 2.5251F));

        flowers.addChild("cube_r5", ModelPartBuilder.create().uv(2, 1).cuboid(-0.1534F, -0.1534F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-10.277F, 0.7868F, 0.1042F, 0.1735F, -0.5206F, 3.0269F));

        ModelPartData bone12 = flowers.addChild("bone12", ModelPartBuilder.create(), ModelTransform.pivot(0.6261F, 0.8565F, 0.1802F));

        bone12.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-0.7534F, -1.1534F, 0.325F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.9245F, 0.8704F, 2.0776F));

        bone12.addChild("cube_r7", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-2.2466F, -1.1534F, 0.325F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-10.507F, 0.8F, 2.5F, -1.8631F, -0.9492F, -1.3156F));

        ModelPartData branch = Head.addChild("branch", ModelPartBuilder.create(), ModelTransform.of(3.7575F, -5.7137F, -0.6F, 0.0057F, -0.0433F, -0.131F));

        branch.addChild("cube_r8", ModelPartBuilder.create().uv(38, 7).cuboid(-1.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5381F, 0.1913F, 0.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData bone = branch.addChild("bone", ModelPartBuilder.create(), ModelTransform.of(2.2343F, -0.625F, 0.0F, 0.0F, 0.3491F, 0.0F));

        bone.addChild("cube_r9", ModelPartBuilder.create().uv(47, 7).cuboid(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.4981F, -0.0436F, 0.0F, -0.2608F, -0.0226F, -0.0843F));

        ModelPartData bone4 = bone.addChild("bone4", ModelPartBuilder.create(), ModelTransform.of(0.0F, -0.0015F, -0.0113F, 0.2251F, -1.0408F, -1.2643F));

        bone4.addChild("cube_r10", ModelPartBuilder.create().uv(38, 5).cuboid(-0.5038F, -0.4128F, -0.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.4981F, -0.0421F, 0.0113F, -0.2608F, -0.0226F, -0.0843F));

        ModelPartData leaf2 = bone4.addChild("leaf2", ModelPartBuilder.create(), ModelTransform.of(-0.3405F, 0.3426F, 0.1592F, 0.5236F, -0.0436F, 2.3998F));

        leaf2.addChild("cube_r11", ModelPartBuilder.create().uv(38, 3).cuboid(-0.4076F, -0.8257F, 0.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.1209F, -0.017F, -0.429F, -0.5226F, -0.0226F, -0.0843F));

        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create(), ModelTransform.of(2.0287F, -0.0945F, 0.0F, 0.4392F, -0.498F, -0.1314F));

        bone2.addChild("cube_r12", ModelPartBuilder.create().uv(47, 5).cuboid(0.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.69F, 0.4131F, 0.0F, 0.0F, 0.0F, -0.6545F));

        ModelPartData bone5 = bone2.addChild("bone5", ModelPartBuilder.create(), ModelTransform.of(1.655F, -1.0F, -0.2806F, 0.775F, 0.7697F, 1.1038F));

        bone5.addChild("cube_r13", ModelPartBuilder.create().uv(38, 12).cuboid(0.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5405F, 0.4131F, -0.375F, 0.0F, 0.0F, -0.6545F));

        ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(1.3367F, -0.9675F, 0.0F));

        bone3.addChild("cube_r14", ModelPartBuilder.create().uv(38, 1).cuboid(0.0516F, -0.6384F, -0.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.48F, -0.4363F, -0.829F));

        ModelPartData leaf = bone3.addChild("leaf", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        leaf.addChild("cube_r15", ModelPartBuilder.create().uv(36, 10).cuboid(-0.3484F, -0.1384F, -0.5F, 4.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3674F, -0.9028F, -1.2621F));

        ModelPartData branch2 = Head.addChild("branch2", ModelPartBuilder.create(), ModelTransform.of(-3.7575F, -5.7137F, -0.6F, 0.0057F, 0.0433F, 0.131F));

        branch2.addChild("cube_r16", ModelPartBuilder.create().uv(20, 9).cuboid(-2.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5381F, 0.1913F, 0.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData bone6 = branch2.addChild("bone6", ModelPartBuilder.create(), ModelTransform.of(-2.2343F, -0.625F, 0.0F, 0.0F, -0.3491F, 0.0F));

        bone6.addChild("cube_r17", ModelPartBuilder.create().uv(30, 6).cuboid(-0.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.4981F, -0.0436F, 0.0F, -0.2608F, 0.0226F, 0.0843F));

        ModelPartData bone7 = bone6.addChild("bone7", ModelPartBuilder.create(), ModelTransform.of(0.0F, -0.0015F, -0.0113F, 0.2251F, 1.0408F, 1.2643F));

        bone7.addChild("cube_r18", ModelPartBuilder.create().uv(20, 6).cuboid(-2.4962F, -0.4128F, -0.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.4981F, -0.0421F, 0.0113F, -0.2244F, -0.2729F, -0.0219F));

        ModelPartData leaf3 = bone7.addChild("leaf3", ModelPartBuilder.create(), ModelTransform.of(0.3405F, 0.3426F, 0.1592F, 0.5236F, 0.0436F, -2.3998F));

        leaf3.addChild("cube_r19", ModelPartBuilder.create().uv(30, 4).cuboid(-3.2924F, -0.4257F, 0.1F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.1209F, -0.017F, -0.429F, -0.2104F, 0.543F, 0.6454F));

        leaf3.addChild("cube_r20", ModelPartBuilder.create().uv(20, 4).cuboid(-4.1924F, 0.4743F, 0.2F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.1209F, -0.017F, -0.429F, 0.8997F, -0.5369F, -3.0221F));

        ModelPartData bone8 = bone6.addChild("bone8", ModelPartBuilder.create(), ModelTransform.of(-2.0287F, -0.0945F, 0.0F, 0.4392F, 0.498F, 0.1314F));

        bone8.addChild("cube_r21", ModelPartBuilder.create().uv(30, 2).cuboid(-2.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.69F, 0.4131F, 0.0F, 0.0F, 0.0F, 0.6545F));

        ModelPartData bone9 = bone8.addChild("bone9", ModelPartBuilder.create(), ModelTransform.of(-1.655F, -1.0F, -0.2806F, 0.775F, -0.7697F, -1.1038F));

        bone9.addChild("cube_r22", ModelPartBuilder.create().uv(20, 11).cuboid(-2.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5405F, 0.4131F, -0.375F, 0.0F, 0.0F, 0.6545F));

        ModelPartData bone10 = bone8.addChild("bone10", ModelPartBuilder.create(), ModelTransform.pivot(-1.3367F, -0.9675F, 0.0F));

        bone10.addChild("cube_r23", ModelPartBuilder.create().uv(20, 2).cuboid(-3.0516F, -0.6384F, -0.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.48F, 0.4363F, 0.829F));

        ModelPartData leaf4 = bone10.addChild("leaf4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        leaf4.addChild("cube_r24", ModelPartBuilder.create().uv(20, 0).cuboid(-2.392F, 0.1107F, -1.8008F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.4015F, -1.3583F, 0.2707F, 0.0852F, -0.2979F, 0.2401F));
        return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		head.render(matrices, vertices, light, overlay, color);
	}
}