package net.chexz.create_quantum.particle;

import net.chexz.create_quantum.CreateQuantum;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, CreateQuantum.MOD_ID);

    public static final Supplier<SimpleParticleType> HEART_PARTICLES =
            PARTICLES.register("heart_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus){
        PARTICLES.register(eventBus);
    }
}
