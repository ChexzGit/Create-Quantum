package net.chexz.create_quantum.effect;

import net.chexz.create_quantum.CreateQuantum;
import net.chexz.create_quantum.networking.ModAttachments;
import net.chexz.create_quantum.networking.packet.StrikeTargetwLightningS2C;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@Mod(value = CreateQuantum.MOD_ID)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = CreateQuantum.MOD_ID)
public class EffectHandler {
    @SubscribeEvent
    public static void onEntityHit(LivingIncomingDamageEvent event){
        if(event.getSource().getEntity() == null) return;
        LivingEntity source = (LivingEntity) event.getSource().getEntity();
        if(source.hasEffect(ModEffects.LIFESTEAL_EFFECT) &&
                source.getItemBySlot(EquipmentSlot.MAINHAND).getDisplayName().getString().equals("[Heartblow]")){
           source.heal(event.getAmount()/2);
        }
        if(source.hasEffect(ModEffects.OVERCHARGED_EFFECT)){
            PacketDistributor.sendToServer(new StrikeTargetwLightningS2C(source.getItemBySlot(EquipmentSlot.MAINHAND)));
            source.removeEffect(ModEffects.OVERCHARGED_EFFECT);
        }

        if(event.getSource().getDirectEntity() instanceof Projectile arrow &&
                arrow.getWeaponItem().getDisplayName().getString().equals("[Sacredshot]") &&
                source.hasEffect(ModEffects.SNIPERMODE_EFFECT)
        ){

        }

        if(source.getItemBySlot(EquipmentSlot.MAINHAND).getDisplayName().getString().equals("[Heartblow]")){
            LivingEntity player = source;

            float newCooldownHeartblowMain = source.getData(ModAttachments.HEARTBLOW_COOLDOWN.get());

            newCooldownHeartblowMain -= 0.5f*event.getAmount()/9;
            if(newCooldownHeartblowMain <= 0.0) newCooldownHeartblowMain = 0.0f;

            player.setData(ModAttachments.HEARTBLOW_COOLDOWN.get(), newCooldownHeartblowMain);
        }
    }
}
