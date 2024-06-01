package sypztep.arctechz.mixin.featureMerge.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityAttachmentType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sypztep.arctechz.ModConfig;

@Mixin(ItemEntityRenderer.class)
public abstract class ItemEntityRendererMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void renderCustomName(ItemEntity itemEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        if (!ModConfig.featuerMerge)
            return;
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        double d = client.getEntityRenderDispatcher().getSquaredDistanceToCamera(itemEntity);
        if (itemEntity.hasCustomName()) {
            if (player == null) {
                return; // If there's no player, we can't render the name following the player.
            }
            if (d > ModConfig.viewItemDistance)
                return;
            Vec3d vec3d = itemEntity.getAttachments().getPointNullable(EntityAttachmentType.NAME_TAG, 0, itemEntity.getYaw(g));
            if (vec3d != null) {
                Text name = itemEntity.getCustomName();
                TextRenderer textRenderer = ((ItemEntityRenderer) (Object) this).getTextRenderer();
                matrixStack.push();
                matrixStack.translate(vec3d.x, vec3d.y + 0.5, vec3d.z);
                matrixStack.multiply(client.getEntityRenderDispatcher().getRotation());
                matrixStack.scale(-0.025F, -0.025F, 0.025F);
                Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
                float backgroundOpacity = client.options.getTextBackgroundOpacity(0.25F);
                int backgroundColor = (int) (backgroundOpacity * 255.0F) << 24;
                float j = (float) (-textRenderer.getWidth(name) / 2);
                textRenderer.draw(name, j, 0, Colors.WHITE, false, matrix4f, vertexConsumerProvider, TextRenderer.TextLayerType.NORMAL, backgroundColor, i);

                matrixStack.pop();
            }
        }
    }
}