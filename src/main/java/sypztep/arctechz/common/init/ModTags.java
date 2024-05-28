package sypztep.arctechz.common.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import sypztep.arctechz.Arctechz;

public class ModTags {
    public static class EntityTypes {
        public static final TagKey<EntityType<?>> BLACKLIST_MERGE_ENTITY = TagKey.of(Registries.ENTITY_TYPE.getKey(), Arctechz.id("blacklist_merge_entity"));

    }
    public static class Items {
        public static final TagKey<Item> BLACKLIST_MERGE_ITEM = TagKey.of(Registries.ITEM.getKey(), Arctechz.id("blacklist_merge_item"));
    }
}
