package net.chexz.create_quantum.networking;

import net.chexz.create_quantum.item.ModItems;
import net.chexz.create_quantum.item.custom.HeartblowItem;
import net.chexz.create_quantum.networking.packet.MainAbilityKeyC2S;
import net.chexz.create_quantum.networking.packet.PowerAbilityKeyC2S;
import net.chexz.create_quantum.networking.packet.UtilAbilityKeyC2S;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
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
        if(iPayloadContext.player().getMainHandItem() == heartBlow){

        }
        if(iPayloadContext.player().getMainHandItem() == heavyBolt){

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
}
