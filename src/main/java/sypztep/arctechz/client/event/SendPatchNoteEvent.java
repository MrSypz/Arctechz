package sypztep.arctechz.client.event;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import sypztep.arctechz.ModConfig;
import sypztep.arctechz.client.payload.EnforceConfigMatchPayload;
import sypztep.arctechz.client.payload.PatchNotePayload;

public class SendPatchNoteEvent implements ServerPlayConnectionEvents.Join {
	@Override
	public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
		EnforceConfigMatchPayload.send(handler.getPlayer(), ModConfig.featureMerge);
		PatchNotePayload.send(handler.getPlayer());
	}
}
