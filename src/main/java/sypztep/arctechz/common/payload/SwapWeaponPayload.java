package sypztep.arctechz.common.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Hand;
import sypztep.arctechz.Arctechz;
import sypztep.arctechz.common.component.entity.BackWeaponComponent;

public record SwapWeaponPayload() implements CustomPayload {
    public static final CustomPayload.Id<SwapWeaponPayload> ID = CustomPayload.id("swap");
    public static final PacketCodec<PacketByteBuf, SwapWeaponPayload> CODEC = PacketCodec.unit(new SwapWeaponPayload());
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
    public static void send() {
        ClientPlayNetworking.send(new SwapWeaponPayload());
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<SwapWeaponPayload> {
        @Override
        public void receive(SwapWeaponPayload payload, ServerPlayNetworking.Context context) {
            if (!context.player().isSpectator()) {
                boolean toggled = BackWeaponComponent.isHoldingBackWeapon(context.player());
                BackWeaponComponent.setHoldingBackWeapon(context.player(), false);
                ItemStack itemStack = BackWeaponComponent.getBackWeapon(context.player());
                boolean success = BackWeaponComponent.setBackWeapon(context.player(), context.player().getStackInHand(Hand.MAIN_HAND));
                if (success) {
                    context.player().setStackInHand(Hand.MAIN_HAND, itemStack);
                }
                context.player().clearActiveItem();
                BackWeaponComponent.setHoldingBackWeapon(context.player(), toggled);
            }
        }
    }
}
