package sypztep.arctechz.mixin.backslot;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({TridentItem.class})
public class TridentItemMixin {
    @WrapOperation(
            method = {"onStoppedUsing"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"
            )}
    )
    private boolean tridentfixdupe(World world, Entity entity, Operation<Boolean> operation, @Local(ordinal = 0) ItemStack stack, @Local(ordinal = 0) LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            if (player.getActiveItem().isOf(Items.TRIDENT)) {
                if (EnchantmentHelper.getLevel(Enchantments.RIPTIDE, stack) > 0) {
                    return operation.call(world, entity);
                }
                player.getMainHandStack().decrementUnlessCreative(1,player);
            }
            else return operation.call(world, entity);
        }
        return operation.call(world, entity);
    }
}
