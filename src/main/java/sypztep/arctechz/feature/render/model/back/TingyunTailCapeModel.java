package sypztep.arctechz.feature.render.model.back;

import net.minecraft.client.model.TexturedModelData;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import sypztep.arctechz.feature.ArctechzFeature;
import sypztep.arctechz.feature.render.feature.WiggleFeature;

public class TingyunTailCapeModel extends BackCosmeticModel implements WiggleFeature {
    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(ArctechzFeature.id("tingyuntailcape"), "main");

    public TingyunTailCapeModel(EntityRendererFactory.Context ctx) {
        super(ctx, MODEL_LAYER);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData back = modelPartData.addChild("back", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData backthing = back.addChild("backthing", ModelPartBuilder.create(), ModelTransform.of(2.5774F, 15.0995F, 3.0F, 0.1725F, 0.0427F, 0.0226F));

        backthing.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-3.0F, -5.5F, 0.0F, 6.0F, 13.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.1548F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        backthing.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -5.5F, 0.0F, 6.0F, 13.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        backthing.addChild("bone5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tail = back.addChild("tail", ModelPartBuilder.create().uv(12, 0).cuboid(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.6742F, 2.2761F, 0.7854F, 0.0F, 0.0F));

        ModelPartData bone = tail.addChild("bone", ModelPartBuilder.create().uv(0, 19).cuboid(-2.5F, 0.0F, -1.5F, 5.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0873F, 0.0F, 0.0F));

        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create(), ModelTransform.of(0.0F, 1.75F, 0.0F, -0.1309F, 0.0F, 0.0F));

        ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -0.5935F, -0.2274F));

        ModelPartData bone4 = bone3.addChild("bone4", ModelPartBuilder.create().uv(29, 0).cuboid(-2.5F, 5.25F, -2.0F, 5.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(-1.0F, 6.25F, -1.5F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 5).cuboid(-3.0F, -0.75F, -2.0F, 6.0F, 6.0F, 4.0F, new Dilation(0.0F))
                .uv(18, 8).cuboid(0.0F, -3.75F, -3.0F, 0.0F, 15.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5935F, 0.2274F, -0.3927F, 0.0F, 0.0F));

        bone4.addChild("flap_r1", ModelPartBuilder.create().uv(32, -2).cuboid(-0.5F, -3.75F, -4.0F, 0.0F, 15.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        back.render(matrices, vertices, light, overlay, color);
    }
}