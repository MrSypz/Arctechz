package sypztep.arctechz.mixin.featureMerge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sypztep.arctechz.ModConfig;
import sypztep.arctechz.common.util.ArctechzUtil;

import java.util.List;
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    @Shadow
    private int pickupDelay;
    @Unique
    private static final float RANGE = ModConfig.mergeRange;

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        if (!ModConfig.featureItemMerge)
            return;
        if (!this.getWorld().isClient) {
            List<ItemEntity> nearbyItems = this.getWorld().getEntitiesByClass(
                    ItemEntity.class,
                    this.getBoundingBox().expand(RANGE),
                    item -> item != (Object) this && canMerge((ItemEntity) (Object) this, item)
            );

            for (ItemEntity otherItem : nearbyItems) {
                if (canMerge((ItemEntity) (Object) this, otherItem)) {
                    mergeItems((ItemEntity) (Object) this, otherItem);
                    break; // Exit after merging to prevent excessive merges in one tick
                }
            }
            updateCustomName((ItemEntity) (Object) this);
        }
    }
    @Inject(method = "tryMerge()V", at = @At("HEAD"), cancellable = true)
    private void disableVanillaMerge(CallbackInfo ci) {
        if (!ModConfig.featureItemMerge)
            return;
        ci.cancel();
    }

    @Unique
    private boolean canMerge(ItemEntity entity1, ItemEntity entity2) {
        ItemStack stack1 = entity1.getStack();
        ItemStack stack2 = entity2.getStack();
        return entity1.isAlive() && entity2.isAlive()
                && ItemStack.areItemsAndComponentsEqual(stack1, stack2)
                && this.pickupDelay != 32767 && entity1.getItemAge() != -32768 && entity1.getItemAge() < 6000;
    }

    @Unique
    private static ItemStack merge(ItemStack stack1, ItemStack stack2, int maxCount) {
        int i = Math.min(Math.min(ModConfig.stackSize, maxCount) - stack1.getCount(), stack2.getCount());
        ItemStack itemStack = stack1.copyWithCount(stack1.getCount() + i);
        stack2.decrement(i);
        return itemStack;
    }

    @Unique
    private static void merge(ItemEntity targetEntity, ItemStack stack1, ItemStack stack2) {
        ItemStack itemStack = merge(stack1, stack2, ModConfig.stackSize);
        targetEntity.setStack(itemStack);
    }

    @Unique
    private void mergeItems(ItemEntity entity1, ItemEntity entity2) {
        ItemStack stack1 = entity1.getStack();
        ItemStack stack2 = entity2.getStack();
        int totalAmount = stack1.getCount() + stack2.getCount();

        if (totalAmount <= ModConfig.stackSize) {
            merge(entity2, stack2, stack1);
        } else {
            int remainder = totalAmount - ModConfig.stackSize;
            stack2.setCount(ModConfig.stackSize);
            stack1.setCount(remainder);
            updateCustomName(entity1); // Update the custom name of the remaining entity
        }
        updateCustomName(entity2);
    }



    @Unique
    private void updateCustomName(ItemEntity entity) {
        ItemStack stack = entity.getStack();
        String itemName = stack.getName().getString();
        int count = stack.getCount();
        entity.setCustomName(ArctechzUtil.createCustomName(count, itemName));
    }
}





