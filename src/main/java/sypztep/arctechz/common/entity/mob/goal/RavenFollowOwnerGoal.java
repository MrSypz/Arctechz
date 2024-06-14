package sypztep.arctechz.common.entity.mob.goal;

import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.passive.TameableEntity;
import sypztep.arctechz.common.entity.mob.RavenEntity;

public class RavenFollowOwnerGoal extends FollowOwnerGoal {
    private final TameableEntity tameable;
    public RavenFollowOwnerGoal(TameableEntity tameable, double speed, float minDistance, float maxDistance) {
        super(tameable, speed, minDistance, maxDistance);
        this.tameable = tameable;
    }

    @Override
    public boolean canStart() {
        return !(Boolean)this.tameable.getDataTracker().get(RavenEntity.GOING_TO_RECEIVER) && super.canStart();
    }
    @Override
    public boolean shouldContinue() {
        return !((Boolean) this.tameable.getDataTracker().get(RavenEntity.GOING_TO_RECEIVER)) && super.shouldContinue();
    }
}