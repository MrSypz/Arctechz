package sypztep.arctechz.common.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import sypztep.arctechz.Arctechz;
import sypztep.arctechz.common.entity.mob.RavenEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModEntityTypes {
    private static final Map<EntityType<?>, Identifier> ENTITY_TYPES = new LinkedHashMap<>();
    public static final EntityType<RavenEntity> RAVEN = createRaven("raven", RavenEntity.createRavenAttributes(), EntityType.Builder.create(RavenEntity::new, SpawnGroup.CREATURE).dimensions(EntityType.BEE.getWidth(),EntityType.BEE.getHeight()).build());
    private static <T extends LivingEntity> EntityType<T> createRaven(String name, DefaultAttributeContainer.Builder attributes, EntityType<T> type) {
        FabricDefaultAttributeRegistry.register(type, attributes);
        ENTITY_TYPES.put(type, Arctechz.id(name));
        return type;
    }
    public static void init() {
        ENTITY_TYPES.keySet().forEach(entityType -> Registry.register(Registries.ENTITY_TYPE, ENTITY_TYPES.get(entityType), entityType));
    }
}
