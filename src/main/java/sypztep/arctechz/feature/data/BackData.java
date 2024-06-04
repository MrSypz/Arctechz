package sypztep.arctechz.feature.data;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import sypztep.arctechz.feature.ArctechzFeature;
import sypztep.arctechz.feature.render.model.back.BackCosmeticModel;

import java.util.function.Function;


public class BackData {
	private final Function<EntityRendererFactory.Context, BackCosmeticModel> model;
	private final Identifier texture;

	public BackData(Function<EntityRendererFactory.Context, BackCosmeticModel> model, String textureName) {
		this.model = model;
		this.texture = ArctechzFeature.id("textures/feature/" + textureName + ".png");
	}

	public BackCosmeticModel createModel(EntityRendererFactory.Context ctx) {
		return model.apply(ctx);
	}

	public Identifier getTexture() {
		return texture;
	}
}
