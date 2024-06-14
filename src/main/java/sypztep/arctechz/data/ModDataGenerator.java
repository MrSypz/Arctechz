package sypztep.arctechz.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import sypztep.arctechz.data.provider.ModEntityTypeTagProvider;
import sypztep.arctechz.data.provider.ModItemTagProvider;
import sypztep.arctechz.data.provider.ModLanguageGenerator;

public class ModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModEntityTypeTagProvider::new);
		pack.addProvider(ModLanguageGenerator::new);
		pack.addProvider(ModItemTagProvider::new);
	}
}
