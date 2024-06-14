package sypztep.arctechz.common.init;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import sypztep.arctechz.Arctechz;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> ALLOW_BACKSLOT_ITEM = TagKey.of(Registries.ITEM.getKey(), Arctechz.id("allow_backslot_item"));
    }
}
