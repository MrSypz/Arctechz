package sypztep.arctechz.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.util.ActionResult;
import org.lwjgl.glfw.GLFW;
import sypztep.arctechz.Arctechz;
import sypztep.arctechz.client.event.ClientHandleTick;
import sypztep.arctechz.client.payload.HandleDropSlotPayload;
import sypztep.arctechz.common.util.WeaponSlotCallback;

public class ArctechzClient implements ClientModInitializer {
    public static final KeyBinding WEAPON_KEYBINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding("key." + Arctechz.MODID + ".select_weapon", GLFW.GLFW_KEY_UNKNOWN, "key.categories." + Arctechz.MODID));
    public static final KeyBinding SWAP_KEYBINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding("key." + Arctechz.MODID + ".swap_weapon", GLFW.GLFW_KEY_G, "key.categories." + Arctechz.MODID));
    private static ActionResult interact(PlayerEntity player, ItemStack stack) {
        return stack.getItem() instanceof TridentItem ? ActionResult.FAIL : ActionResult.PASS;
    }
    @Override
    public void onInitializeClient() {
        initPayloads();
        ClientHandleTick.init();
        WeaponSlotCallback.EVENT.register(ArctechzClient::interact);
    }
    private void initPayloads() {
        ClientPlayNetworking.registerGlobalReceiver(HandleDropSlotPayload.ID, new HandleDropSlotPayload.Receiver());
    }
}
