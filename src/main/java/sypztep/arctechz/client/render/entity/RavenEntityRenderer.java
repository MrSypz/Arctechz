package sypztep.arctechz.client.render.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import sypztep.arctechz.Arctechz;
import sypztep.arctechz.common.entity.mob.RavenEntity;

public class RavenEntityRenderer extends GeoEntityRenderer<RavenEntity> {
    @Override
    public Identifier getTextureLocation(RavenEntity animatable) {
        return super.getTextureLocation(animatable);
    }

    private ItemStack itemStack;
    private VertexConsumerProvider vertexConsumerProvider;

    public RavenEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RavenEntityModel());
    }

    @Override
    public void preRender(MatrixStack poseStack, RavenEntity ravenEntity, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.itemStack = ravenEntity.getEquippedStack(EquipmentSlot.MAINHAND);
        this.vertexConsumerProvider = bufferSource;
        super.preRender(poseStack, ravenEntity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public @Nullable RenderLayer getRenderType(RavenEntity animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return RenderLayer.getEntityTranslucent(texture, true);
    }

    @Override
    public void renderRecursively(MatrixStack poseStack, RavenEntity animatable, GeoBone bone, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("body")) {
            poseStack.push();
            poseStack.translate(bone.getPosX() / -16, bone.getPosY() / 16 + 0.12f, -0.35f);
            poseStack.scale(0.5f, 0.5f, 0.5f);
            poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(bone.getRotX()));
            poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(bone.getRotY()));
            poseStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(bone.getRotZ()));

            MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformationMode.FIRST_PERSON_RIGHT_HAND, packedLight, packedOverlay, poseStack, this.vertexConsumerProvider, animatable.getWorld(), packedLight);
            poseStack.pop();

            // restore the render buffer - GeckoLib expects this state otherwise you'll have weird texture issues
            buffer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(getTextureLocation(animatable)));
        }

        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
