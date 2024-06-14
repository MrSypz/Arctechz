package sypztep.arctechz.feature.render.model.hat;// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import sypztep.arctechz.feature.ArctechzFeature;

public class HutaoHat extends OverheadModel {
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(ArctechzFeature.id("hutao_hat"), "main");
	public HutaoHat(EntityRendererFactory.Context ctx) {
		super(ctx, MODEL_LAYER);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData partdefinition = modelData.getRoot();

		ModelPartData Head = partdefinition.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData hutaohat = Head.addChild("hutaohat", ModelPartBuilder.create().uv(84, 38).cuboid(-5.5F, -0.6792F, -6.1281F, 11.0F, 1.0F, 11.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, -5.4016F, 0.2662F, -0.1309F, 0.0F, 0.0F));

        hutaohat.addChild("cube_r1", ModelPartBuilder.create().uv(112, 7).cuboid(-1.3498F, -1.9353F, -1.2741F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(6.8904F, -1.2903F, -0.8259F, -1.4906F, 0.0358F, -0.2387F));

        hutaohat.addChild("cube_r2", ModelPartBuilder.create().uv(112, 7).cuboid(-0.25F, -1.25F, -1.5F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.2658F, -2.0017F, -2.6573F, -0.7103F, 0.3173F, -0.0022F));

        hutaohat.addChild("cube_r3", ModelPartBuilder.create().uv(112, 7).cuboid(-0.0445F, -1.3804F, -1.4211F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.4485F, -5.1468F, -2.3831F, -0.647F, 0.6473F, -0.2796F));

        hutaohat.addChild("cube_r4", ModelPartBuilder.create().uv(121, 39).cuboid(0.025F, -5.0F, -1.75F, 0.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.6102F, -0.2479F, -0.9959F, -0.0765F, -0.1052F, 3.0844F));

        hutaohat.addChild("cube_r5", ModelPartBuilder.create().uv(113, 12).cuboid(0.0F, -4.9829F, -3.7611F, 0.0F, 6.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -1.0171F, -1.2389F, 0.1171F, 0.3821F, 0.1817F));

        hutaohat.addChild("cube_r6", ModelPartBuilder.create().uv(122, 10).cuboid(-0.5F, 0.6F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.3492F, -6.0618F, 0.2182F, 0.0F, 0.0F));

        hutaohat.addChild("cube_r7", ModelPartBuilder.create().uv(118, 13).cuboid(-1.5F, -2.4179F, -0.0948F, 3.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.3388F, -5.5878F, 0.2182F, 0.0F, 0.0F));

        hutaohat.addChild("cube_r8", ModelPartBuilder.create().uv(101, 0).cuboid(-4.5F, -2.404F, 0.0838F, 9.0F, 3.0F, 4.5F, new Dilation(0.1F)), ModelTransform.of(0.0F, -1.2898F, -0.4593F, -0.2225F, 0.0F, 0.0F));

        hutaohat.addChild("cube_r9", ModelPartBuilder.create().uv(96, 29).cuboid(-4.5F, -2.3004F, -4.9469F, 9.0F, 3.0F, 5.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, -1.331F, 0.0187F, 0.0393F, 0.0F, 0.0F));

        ModelPartData bone = hutaohat.addChild("bone", ModelPartBuilder.create(), ModelTransform.of(0.0F, -1.0F, 5.0F, -0.3491F, 0.0F, 0.0F));

        bone.addChild("cube_r10", ModelPartBuilder.create().uv(101, 10).mirrored().cuboid(-1.8923F, 0.3532F, 0.3782F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.5565F, 0.0811F, -0.1917F, 0.7242F, -0.4366F, 0.3109F));

        bone.addChild("cube_r11", ModelPartBuilder.create().uv(101, 10).cuboid(-0.1077F, 0.3532F, 0.3782F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.5565F, 0.0811F, -0.1917F, 0.7242F, 0.4366F, -0.3109F));

        bone.addChild("cube_r12", ModelPartBuilder.create().uv(101, 24).mirrored().cuboid(-4.5F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3665F, 0.1309F, 0.0F));

        bone.addChild("cube_r13", ModelPartBuilder.create().uv(101, 24).cuboid(0.5F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3665F, -0.1309F, 0.0F));

        bone.addChild("cube_r14", ModelPartBuilder.create().uv(101, 19).cuboid(-1.5F, -0.3339F, -1.898F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.6526F, -0.0428F, 0.1484F, 0.0F, 0.0F));

        ModelPartData hair = Head.addChild("hair", ModelPartBuilder.create(), ModelTransform.of(-3.3688F, -4.0705F, 3.2579F, 0.0915F, -0.3042F, -0.0275F));

        hair.addChild("cube_r15", ModelPartBuilder.create().uv(111, 51).cuboid(-3.8056F, 0.179F, -0.2469F, 8.0F, 10.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.078F, 7.2653F, 2.923F, 0.068F, 0.0723F, 0.0085F));

        hair.addChild("cube_r16", ModelPartBuilder.create().uv(92, 21).cuboid(-0.8056F, 0.179F, -1.2469F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.1181F, 6.9827F, 2.8771F, 0.068F, 0.0723F, 0.0085F));

        hair.addChild("cube_r17", ModelPartBuilder.create().uv(92, 10).cuboid(0.25F, -0.25F, -0.5F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.8812F, 0.0705F, 0.2421F, 0.2618F, 0.0F, 0.1745F));

        ModelPartData hair2 = Head.addChild("hair2", ModelPartBuilder.create(), ModelTransform.of(3.3688F, -4.0705F, 3.2579F, 0.0915F, 0.3042F, 0.0275F));

        hair2.addChild("cube_r18", ModelPartBuilder.create().uv(111, 51).mirrored().cuboid(-4.1944F, 0.179F, -0.2469F, 8.0F, 10.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.078F, 7.2653F, 2.923F, 0.068F, -0.0723F, -0.0085F));

        hair2.addChild("cube_r19", ModelPartBuilder.create().uv(92, 21).mirrored().cuboid(-1.1944F, 0.179F, -1.2469F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.1181F, 6.9827F, 2.8771F, 0.068F, -0.0723F, -0.0085F));

        hair2.addChild("cube_r20", ModelPartBuilder.create().uv(92, 10).mirrored().cuboid(-2.25F, -0.25F, -0.5F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.8812F, 0.0705F, 0.2421F, 0.2618F, 0.0F, -0.1745F));

        return TexturedModelData.of(modelData, 128, 128);
	}
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        head.render(matrices, vertices, light, overlay, color);
    }
}