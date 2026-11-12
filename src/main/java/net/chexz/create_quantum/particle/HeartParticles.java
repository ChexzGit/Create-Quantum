package net.chexz.create_quantum.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class HeartParticles extends TextureSheetParticle {
    protected HeartParticles(ClientLevel level, double x, double y, double z, SpriteSet spriteSet, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

        this.friction = 0.8f;
        this.lifetime = Math.max((int)(6.0 / (Math.random() * 0.8 + 0.6)), 1);
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }


        @Override
        public @Nullable Particle createParticle(SimpleParticleType type, ClientLevel level
                , double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new HeartParticles(level, x, y, z, this.spriteSet, xSpeed, ySpeed, zSpeed);
        }
    }
}
