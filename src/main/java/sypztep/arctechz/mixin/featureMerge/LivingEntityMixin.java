package sypztep.arctechz.mixin.featureMerge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sypztep.arctechz.ModConfig;
import sypztep.arctechz.common.init.ModTags;
import sypztep.arctechz.common.util.ArctechzUtil;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    private static final TrackedData<Integer> MERGE_COUNT = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.INTEGER);
    @Unique
    private static final double MERGE_RADIUS = 2.0;
    @Unique
    private static final double NAME_VISIBILITY_RADIUS = 15.0;

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
        if (!ModConfig.featureMerge)
            return;
        if (!this.getWorld().isClient) {
            LivingEntity entity = (LivingEntity) (Object) this;

            if (!isBlacklisted(entity)) {
                updateCustomName(entity);
            }

            List<LivingEntity> nearbyEntities = this.getWorld().getEntitiesByClass(
                    LivingEntity.class,
                    entity.getBoundingBox().expand(MERGE_RADIUS),
                    e -> e != entity && canMerge(entity, e)
            );

            for (LivingEntity otherEntity : nearbyEntities) {
                if (canMerge(entity, otherEntity)) {
                    mergeEntities(entity, otherEntity);
                    break; // Exit after merging to prevent excessive merges in one tick
                }
            }

            // Ensure the custom name is updated after potential merging
            if (!isBlacklisted(entity)) {
                updateCustomName(entity);
            }
        }
    }
    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource source, CallbackInfo ci) {
        if (!ModConfig.featureMerge)
            return;
        LivingEntity entity = (LivingEntity) (Object) this;
        int count = entity.getDataTracker().get(MERGE_COUNT);

        if (count > 1) {
            int newCount = count - 1;
            entity.getDataTracker().set(MERGE_COUNT, newCount);

            if (!isBlacklisted(entity)) {
                updateCustomName(entity);
            }

            LivingEntity newEntity = (LivingEntity) entity.getType().create(entity.getWorld());
            if (newEntity != null) {
                newEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch());
                newEntity.getDataTracker().set(MERGE_COUNT, newCount);

                if (!isBlacklisted(newEntity)) {
                    newEntity.setCustomName(ArctechzUtil.createCustomName(newCount, newEntity.getType().getName().getString()));
                }

                entity.getWorld().spawnEntity(newEntity);
            }
        }
    }

    @Unique
    private boolean canMerge(LivingEntity entity1, LivingEntity entity2) {
        return entity1.isAlive() && entity2.isAlive()
                && entity1.getType() == entity2.getType()
                && entity1.distanceTo(entity2) < MERGE_RADIUS
                && (entity1.isBaby() == entity2.isBaby())
                && !(entity1 instanceof PlayerEntity)
                && !isBlacklisted(entity1);
    }

    @Unique
    private boolean isBlacklisted(LivingEntity entity) {
        return entity.getType().isIn(ModTags.EntityTypes.BLACKLIST_MERGE_ENTITY);
    }

    @Unique
    private void mergeEntities(LivingEntity entity1, LivingEntity entity2) {
        int count1 = entity1.getDataTracker().get(MERGE_COUNT);
        int count2 = entity2.getDataTracker().get(MERGE_COUNT);
        int totalCount = count1 + count2;

        entity1.getDataTracker().set(MERGE_COUNT, totalCount);
        entity2.discard();

        if (!isBlacklisted(entity1)) {
            updateCustomName(entity1);
        }
    }

    @Unique
    private void updateCustomName(LivingEntity entity) {
        int count = entity.getDataTracker().get(MERGE_COUNT);
        String entityName = entity.getType().getName().getString();
        entity.setCustomName(ArctechzUtil.createCustomName(count, entityName));

        boolean isVisible = getNearestPlayer(entity, NAME_VISIBILITY_RADIUS) != null;
        entity.setCustomNameVisible(isVisible);
    }

    @Unique
    private PlayerEntity getNearestPlayer(LivingEntity entity, double maxDistance) {
        return this.getWorld().getClosestPlayer(entity.getX(), entity.getY(), entity.getZ(), maxDistance, false);
    }
}