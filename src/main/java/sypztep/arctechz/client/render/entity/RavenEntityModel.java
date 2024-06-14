
package sypztep.arctechz.client.render.entity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;
import sypztep.arctechz.Arctechz;
import sypztep.arctechz.common.entity.mob.RavenEntity;

public class RavenEntityModel extends GeoModel<RavenEntity> {
    public RavenEntityModel() {
    }
    @Override
    public Identifier getModelResource(RavenEntity object) {
        return Arctechz.id("geo/entity/raven.geo.json");
    }

    @Override
    public Identifier getTextureResource(RavenEntity object) {
        return Arctechz.id("textures/entity/raven/raven_"+ object.getRavenType().toString().toLowerCase() + ".png");
    }

    @Override
    public Identifier getAnimationResource(RavenEntity animatable) {
        return Arctechz.id("animations/entity/raven.animation.json");
    }
    @Override
    public void setCustomAnimations(RavenEntity entity, long instanceId, AnimationState<RavenEntity> animationState) {
        super.setCustomAnimations(entity, instanceId, animationState);
        GeoBone root = this.getAnimationProcessor().getBone("root");
        if(entity.isBaby()) {
            if (root != null) {
                root.setScaleX(0.5f);
                root.setScaleY(0.5f);
                root.setScaleZ(0.5f);
                root.setPosY(-0.1F);
            }
        } else {
            if (root != null) {
                root.setScaleX(1.2f);
                root.setScaleY(1.2f);
                root.setScaleZ(1.2f);
                root.setPosY(0.05F);
            }
        }
    }
}
