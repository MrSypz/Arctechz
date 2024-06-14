package sypztep.arctechz.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import sypztep.arctechz.client.ArctechzClient;
import sypztep.arctechz.common.component.entity.BackWeaponComponent;
import sypztep.arctechz.common.init.ModTags;
import sypztep.arctechz.common.payload.SwapWeaponPayload;

public class BackSlotEvent implements ClientTickEvents.EndTick{
    @Override
    public void onEndTick(MinecraftClient client) {
        if (client.player != null) {
            if (ArctechzClient.WEAPON_KEYBINDING.wasPressed() && BackWeaponComponent.getBackWeapon(client.player).isIn(ModTags.Items.ALLOW_BACKSLOT_ITEM)) {
                BackWeaponComponent.setHoldingBackWeapon(client.player, !BackWeaponComponent.isHoldingBackWeapon(client.player));
            }

            if (ArctechzClient.SWAP_KEYBINDING.wasPressed()) {
                SwapWeaponPayload.send();
            }
        }
    }
}
