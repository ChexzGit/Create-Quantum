package net.chexz.create_quantum.effect;

import net.chexz.create_quantum.CreateQuantum;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, CreateQuantum.MOD_ID);

    public static final Holder<MobEffect> LIFESTEAL_EFFECT = MOB_EFFECTS.register("lifesteal",
            () -> new LifestealEffect(MobEffectCategory.BENEFICIAL,0xf74c2a));
    public static final Holder<MobEffect> OVERCHARGED_EFFECT = MOB_EFFECTS.register("overcharged",
            () -> new OverchargedEffect(MobEffectCategory.BENEFICIAL,0x40f1f7));
    public static final Holder<MobEffect> SNIPERMODE_EFFECT = MOB_EFFECTS.register("sniper_mode",
            () -> new SniperModeEffect(MobEffectCategory.BENEFICIAL,0xfcca03));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
