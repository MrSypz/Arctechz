package sypztep.arctechz.common.init;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import sypztep.arctechz.Arctechz;
import sypztep.arctechz.client.render.particle.RavenFeatherParticle;

public class ModParticles {
    public static final SimpleParticleType RAVEN_FEATHER = add("raven_feather");
    public static final SimpleParticleType RAVEN_FEATHER_ALBINO = add("raven_feather_albino");
    public static final SimpleParticleType RAVEN_FEATHER_GREEN = add("raven_feather_green");
    public static void initParticles() {
        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();
        registry.register(RAVEN_FEATHER, RavenFeatherParticle.Factory::new);
        registry.register(RAVEN_FEATHER_ALBINO, RavenFeatherParticle.Factory::new);
        registry.register(RAVEN_FEATHER_GREEN, RavenFeatherParticle.Factory::new);
    }
    private static SimpleParticleType add(String name) {
        return Registry.register(Registries.PARTICLE_TYPE, Arctechz.id(name), FabricParticleTypes.simple());
    }
}
