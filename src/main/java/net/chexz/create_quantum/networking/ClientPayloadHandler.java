package net.chexz.create_quantum.networking;

import net.chexz.create_quantum.effect.ModEffects;
import net.chexz.create_quantum.item.ModItems;
import net.chexz.create_quantum.networking.packet.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {
    static ItemStack heartBlow = new ItemStack(new ItemLike() {
        @Override
        public Item asItem() {
            return ModItems.HEARTBLOW.get();
        }
    });
    static ItemStack heavyBolt = new ItemStack(new ItemLike() {
        @Override
        public Item asItem() {
            return ModItems.HEAVYBOLT.get();
        }
    });
    static ItemStack sacredShot = new ItemStack(new ItemLike() {
        @Override
        public Item asItem() {
            return ModItems.SACREDSHOT.get();
        }
    });
    public static void handleTestPacket(MainAbilityKeyC2S mainAbilityKeyC2S, IPayloadContext iPayloadContext) {
        //iPayloadContext.player().sendSystemMessage(Component.literal("" + iPayloadContext.player().getItemBySlot(EquipmentSlot.MAINHAND).getDisplayName().getString()));
        //.requestServerCooldown(iPayloadContext.player());

        if(iPayloadContext.player().getItemBySlot(EquipmentSlot.MAINHAND).getDisplayName().getString().equals("[Heartblow]")){
            float cooldown = iPayloadContext.player().getData(ModAttachments.HEARTBLOW_COOLDOWN.get());

            if(cooldown == 0.0f){
                iPayloadContext.player().addEffect(new MobEffectInstance(ModEffects.LIFESTEAL_EFFECT, 200, 1));
                iPayloadContext.player().setData(ModAttachments.HEARTBLOW_COOLDOWN.get(), 60.0f);
            }
        }
        if(iPayloadContext.player().getItemBySlot(EquipmentSlot.MAINHAND).getDisplayName().getString().equals("[Heavybolt]")){
            float cooldown = iPayloadContext.player().getData(ModAttachments.HEAVYBOLT_COOLDOWN.get());

            if(cooldown == 0.0f){
                iPayloadContext.player().addEffect(new MobEffectInstance(ModEffects.OVERCHARGED_EFFECT, 60, 1));
                iPayloadContext.player().setData(ModAttachments.HEAVYBOLT_COOLDOWN.get(), 220.0f);
            }
        }
        if(iPayloadContext.player().getMainHandItem() == sacredShot){

        }
    }
    public static void handleTestPacket(UtilAbilityKeyC2S utilAbilityKeyC2S, IPayloadContext iPayloadContext) {
        if(iPayloadContext.player().getMainHandItem() == heartBlow){

        }
        if(iPayloadContext.player().getMainHandItem() == heavyBolt){

        }
        if(iPayloadContext.player().getMainHandItem() == sacredShot){

        }
    }
    public static void handleTestPacket(PowerAbilityKeyC2S powerAbilityKeyC2S, IPayloadContext iPayloadContext) {
        if(iPayloadContext.player().getMainHandItem() == heartBlow){

        }
        if(iPayloadContext.player().getMainHandItem() == heavyBolt){

        }
        if(iPayloadContext.player().getMainHandItem() == sacredShot){

        }
    }
    public static void handleTestPacket(RequestCooldownS2C requestCooldownS2C, IPayloadContext iPayloadContext) {
        if(iPayloadContext.player().getItemBySlot(EquipmentSlot.MAINHAND).getDisplayName().getString().equals("[Heartblow]"))
            iPayloadContext.player().setData(ModAttachments.HEARTBLOW_COOLDOWN.get(), 60.0f);
        if(iPayloadContext.player().getItemBySlot(EquipmentSlot.MAINHAND).getDisplayName().getString().equals("[Heavybolt]"))
            iPayloadContext.player().setData(ModAttachments.HEAVYBOLT_COOLDOWN.get(), 220.0f);
    }

    public static void handleTestPacket(UpdateCooldownC2S updateCooldownC2S, IPayloadContext iPayloadContext) {
        LivingEntity player = iPayloadContext.player();

        float newCooldownHeartblowMain = iPayloadContext.player().getData(ModAttachments.HEARTBLOW_COOLDOWN.get());

        newCooldownHeartblowMain -= 0.025f;
        if(newCooldownHeartblowMain <= 0.0) newCooldownHeartblowMain = 0.0f;

        player.setData(ModAttachments.HEARTBLOW_COOLDOWN.get(), newCooldownHeartblowMain);

        float newCooldownHeavyboltMain = iPayloadContext.player().getData(ModAttachments.HEAVYBOLT_COOLDOWN.get());

        newCooldownHeavyboltMain -= 0.025f;
        if(newCooldownHeavyboltMain <= 0.0) newCooldownHeavyboltMain = 0.0f;

        player.setData(ModAttachments.HEAVYBOLT_COOLDOWN.get(), newCooldownHeavyboltMain);
    }

    public static void handleTestPacket(StrikeTargetwLightningS2C strikeTargetwLightningC2S, IPayloadContext iPayloadContext) {
        Entity player = iPayloadContext.player();
        int strikes = (int)Math.round(Math.random()*20*(player.fallDistance*0.5f));
        for(int i=0;i<strikes;i++) {
            BlockPos spawn = new BlockPos(iPayloadContext.player().getBlockX(),
                    iPayloadContext.player().getBlockY(),
                    iPayloadContext.player().getBlockZ());

            int dx = (int)Math.round((Math.random()-0.5f)*20*player.fallDistance);
            int dy = (int)Math.round((Math.random()-0.5f)*5*player.fallDistance);
            int dz = (int)Math.round((Math.random()-0.5f)*20*player.fallDistance);

            BlockPos spawn1 = new BlockPos(iPayloadContext.player().getBlockX() + dx,
                    iPayloadContext.player().getBlockY() + dy,
                    iPayloadContext.player().getBlockZ()+ dz);

            EntityType.LIGHTNING_BOLT.spawn(((ServerLevel) iPayloadContext.player().level()),
                    spawn1,
                    MobSpawnType.TRIGGERED);
        }
    }
}
