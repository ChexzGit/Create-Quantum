package net.chexz.create_quantum.networking;

import net.chexz.create_quantum.networking.packet.MainAbilityKeyC2S;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {
    public static void handleTestPacket(MainAbilityKeyC2S mainAbilityKeyC2S, IPayloadContext iPayloadContext) {
        EntityType.LIGHTNING_BOLT.spawn(((ServerLevel) iPayloadContext.player().level()),
                iPayloadContext.player().getOnPos(),
                MobSpawnType.TRIGGERED);
    }
}
