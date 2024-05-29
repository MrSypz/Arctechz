package sypztep.arctechz.mixin.backslot.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sypztep.arctechz.client.slot.WeaponSlot;
import sypztep.arctechz.mixin.util.CreativeSlotAccessor;

@Environment(EnvType.CLIENT)
@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler> {
    @Shadow
    private static ItemGroup selectedTab;

    public CreativeInventoryScreenMixin(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @WrapOperation(method = {"setSelectedTab"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/util/collection/DefaultedList;add(Ljava/lang/Object;)Z", ordinal = 2)})
    private boolean mamy$moveWeaponSlot(DefaultedList<Slot> slots, Object object, Operation<Boolean> operation) {
        if (object instanceof CreativeInventoryScreen.CreativeSlot newSlot) {
            Slot slot = ((CreativeSlotAccessor) newSlot).getSlot();
            if (slot instanceof WeaponSlot) {
                CreativeInventoryScreen.CreativeSlot sl = new CreativeInventoryScreen.CreativeSlot(slot, 0, 127, 20);
                return operation.call(slots, sl);
            }
        }

        return operation.call(slots, object);
    }

    @Inject(method = {"drawBackground"}, at = {@At("TAIL")})
    private void mamy$drawSlots(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        if (selectedTab.getType() == ItemGroup.Type.INVENTORY) {
            int i = this.x + 126;
            int j = this.y + 19;
            context.drawTexture(BACKGROUND_TEXTURE, i, j, 76, 61, 18, 18);
        }
    }
}
