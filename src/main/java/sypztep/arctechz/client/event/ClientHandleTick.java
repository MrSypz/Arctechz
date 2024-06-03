package sypztep.arctechz.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import sypztep.arctechz.client.ArctechzClient;
import sypztep.arctechz.common.component.entity.BackWeaponComponent;
import sypztep.arctechz.common.init.ModTags;
import sypztep.arctechz.common.payload.SwapWeaponPayload;

public class ClientHandleTick {
    public static void init(){
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            if (client.player != null) {
                if (ArctechzClient.WEAPON_KEYBINDING.wasPressed() && BackWeaponComponent.getBackWeapon(client.player).isIn(ModTags.Items.ALLOW_BACKSLOT_ITEM)) {
                    BackWeaponComponent.setHoldingBackWeapon(client.player, !BackWeaponComponent.isHoldingBackWeapon(client.player));
                }

                if (ArctechzClient.SWAP_KEYBINDING.wasPressed() && client.player.getMainHandStack().isIn(ModTags.Items.ALLOW_BACKSLOT_ITEM)) {
                    SwapWeaponPayload.send();
                }
            }
        });
    }

}
