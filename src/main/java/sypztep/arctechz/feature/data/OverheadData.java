package sypztep.arctechz.feature.data;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import sypztep.arctechz.feature.ArctechzFeature;
import sypztep.arctechz.feature.render.model.hat.OverheadModel;

import java.util.function.Function;


public class OverheadData {
	private final Function<EntityRendererFactory.Context, OverheadModel> model;
	private final Identifier texture;

	public OverheadData(Function<EntityRendererFactory.Context, OverheadModel> model, String textureName) {
		this.model = model;
		this.texture = ArctechzFeature.id("textures/feature/" + textureName + ".png");
	}

	public OverheadModel createModel(EntityRendererFactory.Context ctx) {
		return model.apply(ctx);
	}

	public Identifier getTexture() {
		return texture;
	}
}
