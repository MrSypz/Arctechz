package sypztep.arctechz.client.render.feature;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.util.math.RotationAxis;
import sypztep.arctechz.common.api.CustomItemFeature;
import sypztep.arctechz.common.component.entity.BackWeaponComponent;

public class AdditionItemFeatureRenderer extends HeldItemFeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private final HeldItemRenderer heldItemRenderer;

    public AdditionItemFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, HeldItemRenderer heldItemRenderer) {
        super(context, heldItemRenderer);
        this.heldItemRenderer = heldItemRenderer;
    }
    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, AbstractClientPlayerEntity livingEntity, float f, float g, float h, float j, float k, float l) {
        if (!BackWeaponComponent.isHoldingBackWeapon(livingEntity)) {
            ItemStack stack = BackWeaponComponent.getBackWeapon(livingEntity);
            if (!stack.isEmpty()) {
                matrixStack.push();
                if (stack.getItem() instanceof TridentItem) {
                    matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(40.0F));
                    matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(52.0F));
                    matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-25.0F));
                    matrixStack.translate(-0.26D, 0.0D, 0.0D);
                    matrixStack.scale(1.0F, -1.0F, -1.0F);
                    heldItemRenderer.renderItem(livingEntity, stack, ModelTransformationMode.THIRD_PERSON_RIGHT_HAND, false, matrixStack, vertexConsumerProvider, i);
                } else if (stack.getItem() instanceof CustomItemFeature custom) {
                    if (custom.getmodelTransform() == ModelTransformationMode.HEAD) {
                        heldItemRenderer.renderItem(livingEntity, stack, ModelTransformationMode.HEAD, false, matrixStack, vertexConsumerProvider, i);
                        matrixStack.pop();
                        return;
                    }
                    matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(custom.getRotation()[0]));
                    matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(custom.getRotation()[1]));
                    matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(custom.getRotation()[2]));
                    matrixStack.translate(custom.getTranslation()[0], custom.getTranslation()[1], custom.getTranslation()[2]);
                    matrixStack.scale(custom.getScale()[0], custom.getScale()[1], custom.getScale()[2]);
                    heldItemRenderer.renderItem(livingEntity, stack, custom.getmodelTransform(), false, matrixStack, vertexConsumerProvider, i);
                }
                matrixStack.pop();
            }
        }
    }
}
