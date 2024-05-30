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
    }
    private static void addcallbackslot(TranslationBuilder translationBuilder,String message) {
        translationBuilder.add(Arctechz.id("backslot.feature.dropfail").toString(),message);
    }
    private static void addEnchantment (TranslationBuilder translationBuilder, Enchantment enchantment, String name, String description) {
        translationBuilder.add(enchantment, name);
        translationBuilder.add(enchantment.getTranslationKey() + ".desc", description);
    }

    private static void addDeathMessage (TranslationBuilder translationBuilder, String base, String message) {
        translationBuilder.add("death.attack." + base, "%1$s " + message + " %2$s");
        translationBuilder.add("death.attack." + base + ".item", "%1$s " + message + " %2$s using %3$s");
    }
}
