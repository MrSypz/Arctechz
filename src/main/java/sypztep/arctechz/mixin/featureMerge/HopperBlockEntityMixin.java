package sypztep.arctechz.mixin.featureMerge;

import net.minecraft.block.entity.Hopper;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.entity.HopperBlockEntity.transfer;

@Mixin(HopperBlockEntity.class)
public abstract class HopperBlockEntityMixin {
    @Inject(method = "extract(Lnet/minecraft/inventory/Inventory;Lnet/minecraft/entity/ItemEntity;)Z", at = @At("HEAD"), cancellable = true)
    private static void extract(Inventory inventory, ItemEntity itemEntity, CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemStack = itemEntity.getStack();
        boolean inserted = false;
        if (itemStack.getMaxCount() > 1) {
            int transferAmount = Math.min(itemStack.getCount(), 64);
            ItemStack transferStack = itemStack.split(transferAmount);
            ItemStack transfer = transfer(null, inventory, transferStack, null);
            if (transfer.isEmpty()) {
                inserted = true;
                if (itemStack.isEmpty()) {
                    itemEntity.discard();
                }
            } else {
                itemStack.increment(transferStack.getCount());
            }
            cir.setReturnValue(inserted);
        } else {
            ItemStack transferStack = itemStack.split(1);
            ItemStack transfer = transfer(null, inventory, transferStack, null);
            if (transfer.isEmpty()) {
                inserted = true;
                if (itemStack.isEmpty()) {
                    itemEntity.discard();
                }
            } else {
                itemStack.increment(transferStack.getCount());
            }
            cir.setReturnValue(inserted);
        }
    }
}

