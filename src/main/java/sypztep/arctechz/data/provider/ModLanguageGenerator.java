package sypztep.arctechz.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryWrapper;
import sypztep.arctechz.Arctechz;

import java.util.concurrent.CompletableFuture;

public class ModLanguageGenerator extends FabricLanguageProvider  {
    public ModLanguageGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        addcallbackslot(translationBuilder,"Fail to drop backslot item switch to main hand first!");

        addconfig(translationBuilder,"title","Arctechz Main");
        addconfig(translationBuilder,"cosmetics","Cosmetics");
        addconfig(translationBuilder,"enum.CosmeticsOptions.ENABLE","Enable");
        addconfig(translationBuilder,"enum.CosmeticsOptions.FIRST_PERSON","First Person");
        addconfig(translationBuilder,"enum.CosmeticsOptions.DISABLE","Disable");
        addkeycategory(translationBuilder, "Arctechz");
        addkeybind(translationBuilder,"select_weapon","Select Weapon");
        addkeybind(translationBuilder,"swap_weapon","Swap Weapon");

        tagtranslate(translationBuilder,"item","allow_backslot_item");

        addentity(translationBuilder,"raven","Raven");
    }
    private static void addcallbackslot(TranslationBuilder translationBuilder,String message) {
        translationBuilder.add(Arctechz.id("backslot.feature.dropfail").toString(),message);
    }

    private static void addconfig(TranslationBuilder translationBuilder,String configname,String message) {
        translationBuilder.add(Arctechz.MODID + ".midnightconfig" + "." + configname,message);
    }
    private static void addkeybind(TranslationBuilder translationBuilder,String configname,String message) {
        translationBuilder.add("key."+ Arctechz.MODID + "." + configname,message);
    }
    private static void addkeycategory(TranslationBuilder translationBuilder,String message) {
        translationBuilder.add("key.categories." + Arctechz.MODID ,message);
    }
    private static void addentity(TranslationBuilder translationBuilder,String configname,String message) {
        translationBuilder.add("entity."+ Arctechz.MODID + "." + configname,message);
    }
    private static void tagtranslate(TranslationBuilder translationBuilder,String type,String message) {
        translationBuilder.add("tag."+ type+ "."+ Arctechz.MODID + ".",message);
    }
}
