package sypztep.arctechz.common.util;

import net.minecraft.item.ItemStack;

public interface WeaponSlotHolder {
    int arctechz$getSlotHolding(ItemStack stack);

    boolean arctechz$tryInsertIntoSlot(int id, ItemStack stack);
}
