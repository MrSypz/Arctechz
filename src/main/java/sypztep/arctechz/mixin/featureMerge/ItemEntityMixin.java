package sypztep.arctechz.mixin.featureMerge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sypztep.arctechz.ModConfig;
import sypztep.arctechz.common.util.ArctechzUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    @Shadow
    public abstract ItemStack getStack();

    @Shadow public abstract int getItemAge();

    @Shadow private int pickupDelay;

    @Shadow private int itemAge;

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        if (!this.getWorld().isClient) {
            List<ItemEntity> list = this.getWorld().getEntitiesByClass(ItemEntity.class, this.getBoundingBox().expand(1.5, 0.5, 1.5), (otherItemEntity) -> true);
            ItemStack stack = this.getStack();
            String itemName = stack.getName().getString();

            this.setCustomName(Text.literal((stack.getCount() + " ")).formatted(Formatting.WHITE)
                    .append(Text.literal("x ").formatted(Formatting.WHITE))
                    .append(Text.literal(itemName).formatted(Formatting.GOLD)).formatted(Formatting.BOLD));
            updateCustomName((ItemEntity)(Object) this);
            for (int i = 0; i < list.size(); i++) {
                ItemEntity item1 = list.get(i);
                for (int j = i + 1; j < list.size(); j++) {
                    ItemEntity item2 = list.get(j);
                    if (item1 != item2 && canMerge(item1, item2)) {
                        mergeItems(item1, item2);
                    }
                }
            }
        }
    }
    @Unique
    private boolean canMerge(ItemEntity entity1, ItemEntity entity2) {
        ItemStack stack1 = entity1.getStack();
        ItemStack stack2 = entity2.getStack();
        return entity1.isAlive() && entity2.isAlive()
                && ItemStack.areItemsAndComponentsEqual(stack1, stack2)
                && this.pickupDelay != 32767 && this.itemAge != -32768 && this.itemAge < 6000;
    }

    @Unique
    private void mergeItems(ItemEntity entity1, ItemEntity entity2) {
        ItemStack stack1 = entity1.getStack();
        ItemStack stack2 = entity2.getStack();
        int totalAmount = stack1.getCount() + stack2.getCount();

        if (totalAmount <= ModConfig.stackSize) {
            stack2.setCount(totalAmount);
            entity1.discard();
        }
        updateCustomName(entity2);
    }
    @Unique
    private void updateCustomName(ItemEntity entity){
        ItemStack stack = entity.getStack();
        String itemName = stack.getName().getString();
        int i = stack.getCount();
        entity.setCustomName(ArctechzUtil.createCustomName(i,itemName));
    }
}




