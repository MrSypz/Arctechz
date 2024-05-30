package sypztep.arctechz.mixin.backslot.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sypztep.arctechz.common.component.entity.BackWeaponComponent;

@Environment(EnvType.CLIENT)
@Mixin({InGameHud.class})
public abstract class InGameHudMixin {
    @Shadow
    protected abstract PlayerEntity getCameraPlayer();
    @Shadow
    protected abstract void renderHotbarItem(DrawContext context, int x, int y, float f, PlayerEntity player, ItemStack stack, int seed);
    @Shadow
    @Final
    private static Identifier HOTBAR_SELECTION_TEXTURE;
    @Shadow
    @Final
    private static Identifier HOTBAR_OFFHAND_RIGHT_TEXTURE;
    @Shadow
    @Final
    private static Identifier HOTBAR_OFFHAND_LEFT_TEXTURE;

    @Inject(method = {"renderHotbar"}, at = {@At("TAIL")})
    private void renderWeaponSlot(DrawContext context, float tickDelta, CallbackInfo ci) {
        PlayerEntity player = this.getCameraPlayer();
        if (player != null) {
            ItemStack stack = BackWeaponComponent.getBackWeapon(player);
            if (!stack.isEmpty()) {
                int i = context.getScaledWindowWidth() / 2;
                int n;
                if (BackWeaponComponent.isHoldingBackWeapon(player)) {
                    context.drawGuiTexture(HOTBAR_OFFHAND_RIGHT_TEXTURE, i - 18, context.getScaledWindowHeight() - 23 - 70, 29, 24);
                    RenderSystem.enableBlend();
                    context.drawGuiTexture(HOTBAR_SELECTION_TEXTURE, i - 12, context.getScaledWindowHeight() - 23 - 70, 24, 23);
                    RenderSystem.defaultBlendFunc();
                    n = i - 90 + 80 + 2;
                    int p = context.getScaledWindowHeight() - 19 - 70;
                    this.renderHotbarItem(context, n, p, tickDelta, player, stack, 1);
                    RenderSystem.disableBlend();
                } else {
                    Arm arm = player.getMainArm().getOpposite();
                    RenderSystem.enableBlend();
                    RenderSystem.defaultBlendFunc();
                    if (arm == Arm.RIGHT) {
                        context.drawGuiTexture(HOTBAR_OFFHAND_LEFT_TEXTURE, i - 91 - 29, context.getScaledWindowHeight() - 23, 29, 24);
                    } else {
                        context.drawGuiTexture(HOTBAR_OFFHAND_RIGHT_TEXTURE, i + 91, context.getScaledWindowHeight() - 23, 29, 24);
                    }

                    n = context.getScaledWindowHeight() - 16 - 3;
                    if (arm == Arm.RIGHT) {
                        this.renderHotbarItem(context, i - 91 - 26, n, tickDelta, player, stack, 0);
                    } else {
                        this.renderHotbarItem(context, i + 91 + 10, n, tickDelta, player, stack, 0);
                    }

                    RenderSystem.disableBlend();
                }
            }

        }
    }

    @WrapOperation(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
    private void selection(DrawContext instance, Identifier texture, int x, int y, int width, int height, Operation<Void> original) {
        if (this.getCameraPlayer() == null || !BackWeaponComponent.isHoldingBackWeapon(this.getCameraPlayer())) {
            original.call(instance, texture, x, y, width, height);
        }
    }
}
