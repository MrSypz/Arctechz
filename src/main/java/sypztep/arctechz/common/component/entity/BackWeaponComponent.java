package sypztep.arctechz.common.component.entity;

import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import sypztep.arctechz.client.ArctechzClient;
import sypztep.arctechz.common.init.ModEntityComponents;
import sypztep.arctechz.common.payload.HoldWeaponPayload;

public class BackWeaponComponent implements AutoSyncedComponent {
    private final PlayerEntity obj;
    private final SimpleInventory backWeapon = new SimpleInventory(1);
    private boolean holdingBackWeapon = false;

    public BackWeaponComponent(PlayerEntity obj) {
        this.obj = obj;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.backWeapon.setStack(0, ItemStack.fromNbtOrEmpty(registryLookup, tag.getCompound("backWeapon")));
        this.holdingBackWeapon = tag.getBoolean("holdingBackWeapon");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.put("backWeapon", (this.backWeapon.getStack(0).encodeAllowEmpty(registryLookup)));
        tag.putBoolean("holdingBackWeapon", this.holdingBackWeapon);
    }

    public ItemStack getBackWeapon() {
        return this.backWeapon.getStack(0);
    }

    public static ItemStack getBackWeapon(PlayerEntity player) {
        return ModEntityComponents.BACK_WEAPON_COMPONENT.get(player).getBackWeapon();
    }

    public boolean setBackWeapon(ItemStack backWeapon) {
        this.backWeapon.setStack(0, backWeapon);
        ModEntityComponents.BACK_WEAPON_COMPONENT.sync(this.obj);
        return true;
    }

    public static boolean setBackWeapon(PlayerEntity player, ItemStack backWeapon) {
        return ModEntityComponents.BACK_WEAPON_COMPONENT.get(player).setBackWeapon(backWeapon);
    }

    public SimpleInventory getBackWeaponInventory() {
        return this.backWeapon;
    }

    public static SimpleInventory getBackWeaponInventory(PlayerEntity player) {
        return ModEntityComponents.BACK_WEAPON_COMPONENT.get(player).getBackWeaponInventory();
    }

    public boolean isHoldingBackWeapon() {
        return this.holdingBackWeapon;
    }

    public static boolean isHoldingBackWeapon(PlayerEntity player) {
        return ModEntityComponents.BACK_WEAPON_COMPONENT.get(player).isHoldingBackWeapon();
    }

    public void setHoldingBackWeapon(boolean holdingBackWeapon) {
        this.holdingBackWeapon = holdingBackWeapon;
        ModEntityComponents.BACK_WEAPON_COMPONENT.sync(this.obj);
    }

    public static void setHoldingBackWeapon(PlayerEntity player, boolean holdingBackWeapon) {
        if (player.getWorld().isClient()) {
                HoldWeaponPayload.send(holdingBackWeapon);
        } else {
            ModEntityComponents.BACK_WEAPON_COMPONENT.get(player).setHoldingBackWeapon(holdingBackWeapon);
        }
    }
}
