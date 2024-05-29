package sypztep.arctechz.mixin.util;

import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ItemEntity.class)
public interface ItemEntityAccessor {
	@Invoker("tryMerge")
	void arctechz$tryMerge(ItemEntity other);
}
