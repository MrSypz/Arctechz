package sypztep.arctechz.mixin.featureMerge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sypztep.arctechz.common.init.ModTags;
import sypztep.arctechz.common.util.ArctechzUtil;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    private static final TrackedData<Integer> MERGE_COUNT = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract boolean isAlive();

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initMergeCount(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(MERGE_COUNT, 1);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        if (!this.getWorld().isClient) {
            LivingEntity entity = (LivingEntity) (Object) this;

            if (!(entity instanceof VillagerEntity)) {
                updateCustomName(entity);
            }

            List<LivingEntity> nearbyEntities = this.getWorld().getEntitiesByClass(LivingEntity.class, entity.getBoundingBox().expand(2.0), e -> e != entity && canMerge(entity, e));

            for (LivingEntity otherEntity : nearbyEntities) {
                if (canMerge(entity, otherEntity)) {
                    mergeEntities(entity, otherEntity);
                    break; // Exit after merging to prevent excessive merges in one tick
                }
            }

            // Ensure the custom name is updated after potential merging
            if (!(entity instanceof VillagerEntity)) {
                updateCustomName(entity);
            }
        }
    }

    private boolean canMerge(LivingEntity entity1, LivingEntity entity2) {
        boolean basicConditions = entity1.isAlive() && entity2.isAlive()
                && entity1.getType() == entity2.getType()
                && entity1.distanceTo(entity2) < 2.0
                || (entity1.isBaby() && entity2.isBaby());

        return basicConditions && !blacklistMob(entity1);
    }

    private boolean blacklistMob(LivingEntity entity) {
        return !entity.getType().isIn(ModTags.EntityTypes.BLACKLIST_MERGE_ENTITY);
    }

    private void mergeEntities(LivingEntity entity1, LivingEntity entity2) {
        // Combine the merge counts
        int count1 = entity1.getDataTracker().get(MERGE_COUNT);
        int count2 = entity2.getDataTracker().get(MERGE_COUNT);
        int totalCount = count1 + count2;

        // Set the combined count to entity1
        entity1.getDataTracker().set(MERGE_COUNT, totalCount);

        // Remove the second entity
        entity2.discard();

        // Update custom name to reflect the new state
        if (!(entity1 instanceof VillagerEntity)) {
            updateCustomName(entity1);
        }
    }

    @Unique
    private void updateCustomName(LivingEntity entity) {
        int count = entity.getDataTracker().get(MERGE_COUNT);
        String entityName = entity.getType().getName().getString();
        entity.setCustomName(ArctechzUtil.createCustomName(count,entityName));
        entity.setCustomNameVisible(true);
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource source, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        int count = entity.getDataTracker().get(MERGE_COUNT);

        if (count > 1) {
            int newCount = count - 1;
            entity.getDataTracker().set(MERGE_COUNT, newCount);

            // Update the custom name to reflect the new count
            if (!(entity instanceof VillagerEntity)) {
                updateCustomName(entity);
            }

            // Spawn a new entity to represent the remaining count
            LivingEntity newEntity = (LivingEntity) entity.getType().create(entity.getWorld());
            if (newEntity != null) {
                newEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch());
                newEntity.getDataTracker().set(MERGE_COUNT, newCount); // Set the remaining count to the new entity

                if (!(newEntity instanceof VillagerEntity)) {
                    newEntity.setCustomName(Text.literal(newCount + " x ")
                            .append(Text.literal(entity.getType().getName().getString()).formatted(Formatting.GOLD).formatted(Formatting.BOLD)));
                }

                entity.getWorld().spawnEntity(newEntity);
            }
        }
    }
}

