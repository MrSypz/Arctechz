package sypztep.arctechz.common.init;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import sypztep.arctechz.Arctechz;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ModSoundEvents {
    Map<SoundEvent, Identifier> SOUND_EVENTS = new LinkedHashMap<>();
    SoundEvent ENTITY_RAVEN_CAW = createSoundEvent("entity.raven.caw");
    static void init() {
        SOUND_EVENTS.keySet().forEach((soundEvent) -> {
            Registry.register(Registries.SOUND_EVENT, SOUND_EVENTS.get(soundEvent), soundEvent);
        });
    }
    private static SoundEvent createSoundEvent(String path) {
        SoundEvent soundEvent = SoundEvent.of(Arctechz.id(path));
        SOUND_EVENTS.put(soundEvent, Arctechz.id(path));
        return soundEvent;
    }
}
