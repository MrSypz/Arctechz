package sypztep.arctechz.feature.render.model.hat;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import sypztep.arctechz.feature.ArctechzFeature;

public class VastoModel extends OverheadModel {
    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(ArctechzFeature.id("vasto_mask_model"), "main");

    public VastoModel(EntityRendererFactory.Context ctx) {
        super(ctx, MODEL_LAYER);
    }


    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData partdefinition = modelData.getRoot();
        ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData mask = head.addChild("armorHead", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 11.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 11).cuboid(-4.0F, -9.0F, -4.5F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F))
                .uv(-1, 0).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 0.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 19).cuboid(-4.0F, -1.0F, -4.5F, 8.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        mask.addChild("armorHead_r1", ModelPartBuilder.create().uv(16, 10).cuboid(-4.0F, -8.0F, 2.0F, 0.0F, 8.0F, 2.0F, new Dilation(0.0F))
                .uv(20, 10).cuboid(4.0F, -8.0F, 2.0F, 0.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 3.1416F, 0.0F, -3.1416F));

        ModelPartData bone2 = mask.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(-5.6493F, -4.7497F, -2.5432F));

        bone2.addChild("bone2_r1", ModelPartBuilder.create().uv(16, 3).cuboid(-0.5862F, -0.5F, 0.4236F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.9895F, -0.9854F, -0.9033F));

        bone2.addChild("bone2_r2", ModelPartBuilder.create().uv(16, 0).cuboid(-2.4236F, -0.5F, -2.5862F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 6).cuboid(-0.4236F, -0.5F, 0.4138F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.5059F, 0.3082F, 0.1665F));

        bone2.addChild("bone2_r3", ModelPartBuilder.create().uv(16, 9).cuboid(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.3689F, 2.5149F, -4.831F, 0.9895F, -0.9854F, -0.9033F));

        ModelPartData bone3 = mask.addChild("bone3", ModelPartBuilder.create(), ModelTransform.of(6.2233F, -4.2516F, -3.5001F, -0.9601F, 0.0182F, -3.1202F));

        bone3.addChild("bone3_r1", ModelPartBuilder.create().uv(16, 3).cuboid(-0.5862F, -0.5F, 0.4236F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5774F, -0.4981F, 0.9569F, 0.9895F, -0.9854F, -0.9033F));

        bone3.addChild("bone3_r2", ModelPartBuilder.create().uv(16, 0).cuboid(-2.4236F, -0.5F, -2.5862F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 6).cuboid(-0.4236F, -0.5F, 0.4138F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5774F, -0.4981F, 0.9569F, 0.5059F, 0.3082F, 0.1665F));

        bone3.addChild("bone3_r3", ModelPartBuilder.create().uv(16, 9).cuboid(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.7916F, 2.0168F, -3.8742F, 0.9895F, -0.9854F, -0.9033F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        head.render(matrices, vertices, light, overlay, color);

    }
}
