package sypztep.arctechz.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import sypztep.arctechz.common.init.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider{

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.ALLOW_BACKSLOT_ITEM)
                .addOptionalTag(ItemTags.AXES)
                .addOptionalTag(ItemTags.SHOVELS)
                .addOptionalTag(ItemTags.HOES)
                .addOptionalTag(ItemTags.PICKAXES)
                .addOptionalTag(ItemTags.SWORDS)
                .add(Items.TRIDENT)
                .add(Items.SHULKER_BOX)
                .add(Items.TOTEM_OF_UNDYING);
    }
}
