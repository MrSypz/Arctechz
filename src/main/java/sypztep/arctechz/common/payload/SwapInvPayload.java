package sypztep.arctechz.common.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.screen.slot.Slot;
import sypztep.arctechz.Arctechz;
import sypztep.arctechz.common.component.entity.BackWeaponComponent;

public record SwapInvPayload(int slotId) implements CustomPayload {
    public static final CustomPayload.Id<SwapInvPayload> ID = CustomPayload.id(Arctechz.id("swap_inv").toString());
    public static final PacketCodec<PacketByteBuf, SwapInvPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, SwapInvPayload::slotId, SwapInvPayload::new);
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
    public static void send(int slotId) {
        ClientPlayNetworking.send(new SwapInvPayload(slotId));
    }
    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<SwapInvPayload> {
        @Override
        public void receive(SwapInvPayload payload, ServerPlayNetworking.Context context) {
            if (!context.player().isSpectator()) {
                if (!context.player().currentScreenHandler.isValid(payload.slotId())) {
                    return;
                }

                Slot slot = context.player().currentScreenHandler.getSlot(payload.slotId());
                ItemStack itemStack = BackWeaponComponent.getBackWeapon(context.player());
                boolean success = BackWeaponComponent.setBackWeapon(context.player(), slot.getStack());
                if (success) {
                    slot.setStack(itemStack);
                }
            }
        }
    }
}
