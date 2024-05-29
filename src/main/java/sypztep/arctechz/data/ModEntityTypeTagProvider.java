package sypztep.arctechz.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryWrapper;
import sypztep.arctechz.common.init.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
	public ModEntityTypeTagProvider(FabricDataOutput output) {
		super(output, CompletableFuture.supplyAsync(BuiltinRegistries::createWrapperLookup));
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		getOrCreateTagBuilder(ModTags.EntityTypes.BLACKLIST_MERGE_ENTITY)
				.addOptionalTag(ConventionalEntityTypeTags.BOATS)
				.addOptionalTag(ConventionalEntityTypeTags.MINECARTS)
				.addOptionalTag(ConventionalEntityTypeTags.BOSSES)
				.add(EntityType.END_CRYSTAL)
				.add(EntityType.ARMOR_STAND)
				.add(EntityType.HORSE)
				.add(EntityType.DONKEY)
				.add(EntityType.VILLAGER);
	}
}
