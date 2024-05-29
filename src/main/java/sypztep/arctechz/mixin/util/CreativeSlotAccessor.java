package sypztep.arctechz.mixin.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Environment(EnvType.CLIENT)
@Mixin({CreativeInventoryScreen.CreativeSlot.class})
public interface CreativeSlotAccessor {
    @Accessor("slot")
    Slot getSlot();
}
