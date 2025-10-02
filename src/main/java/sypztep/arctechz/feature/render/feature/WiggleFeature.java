package sypztep.arctechz.feature.render.feature;

import net.minecraft.client.network.AbstractClientPlayerEntity;

public interface WiggleFeature {
    default void applyTailPhysics(AbstractClientPlayerEntity entity, float tickDelta) {

    }
}