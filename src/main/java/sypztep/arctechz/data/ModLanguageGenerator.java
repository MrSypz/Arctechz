package sypztep.arctechz.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryWrapper;
import sypztep.arctechz.Arctechz;

import java.util.concurrent.CompletableFuture;

public class ModLanguageGenerator extends FabricLanguageProvider  {
    protected ModLanguageGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        addcallbackslot(translationBuilder,"Fail to drop backslot item switch to main hand first!");
        addconfig(translationBuilder,"title","Arctechz Main");

        addconfig(translationBuilder,"featureMerge","Merge Feature");
        addconfig(translationBuilder,"stackSize","Max Merge Size");
        addconfig(translationBuilder,"mergeRange","Max Merge Range");
        addconfig(translationBuilder,"cosmetics","Cosmetics");
        addconfig(translationBuilder,"enum.CosmeticsOptions.ENABLE","Enable");
        addconfig(translationBuilder,"enum.CosmeticsOptions.FIRST_PERSON","First Person");
        addconfig(translationBuilder,"enum.CosmeticsOptions.DISABLE","Disable");

        addconfig(translationBuilder,"category.client","Arctechz Client Side");
        addconfig(translationBuilder,"viewItemDistance","viewItemDistance");
    }
    private static void addcallbackslot(TranslationBuilder translationBuilder,String message) {
        translationBuilder.add(Arctechz.id("backslot.feature.dropfail").toString(),message);
    }
    private static void addconfig(TranslationBuilder translationBuilder,String configname,String message) {
        translationBuilder.add(Arctechz.MODID + ".midnightconfig" + "." + configname,message);
    }
}
