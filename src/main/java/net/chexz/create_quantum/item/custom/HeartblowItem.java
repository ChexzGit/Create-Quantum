package net.chexz.create_quantum.item.custom;

import net.chexz.create_quantum.networking.ModAttachments;
import net.chexz.create_quantum.networking.packet.StrikeTargetwLightningS2C;
import net.chexz.create_quantum.networking.packet.UpdateCooldownC2S;
import net.chexz.create_quantum.util.ModTags;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HeartblowItem extends SwordItem {
    public ServerBossEvent barMain =
            new ServerBossEvent(Component.literal("Heartblow Main Cooldown"),
                    BossEvent.BossBarColor.RED,
                    BossEvent.BossBarOverlay.NOTCHED_20
            );


    public HeartblowItem(Tier tier, Item.Properties properties) {
        super(tier, properties.component(DataComponents.TOOL, createToolProperties()));
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        int j = 0;
        IItemHandler inventory = entity.getCapability(Capabilities.ItemHandler.ENTITY);

        if (inventory != null) {
            for (int i = 0; i < inventory.getSlots(); i++) {
                ItemStack stack2 = inventory.getStackInSlot(i);

                if (!stack2.isEmpty() && isLegendary(stack2)) {
                    j++;
                }
            }
        }

        if(isLegendary(stack) && j > 1){
            ItemEntity spawnItem = new ItemEntity(
                    level,
                    entity.getX(),
                    entity.getY(),
                    entity.getZ(),
                    stack
            );
            spawnItem.setPickUpDelay(40);
            List<ItemEntity> list = List.of(spawnItem);
            entity.captureDrops(list);
            //level.addFreshEntity(spawnItem);g

            //inventory.extractItem(slotId,64,false);

        }

        PacketDistributor.sendToServer(new UpdateCooldownC2S());

        if(!level.isClientSide()){
            if(barMain.getProgress() == 0.0f) {
                if(entity instanceof ServerPlayer player) barMain.removePlayer(player);
            }
            else {
                if(entity instanceof ServerPlayer player) barMain.addPlayer(player);
            }

            barMain.setProgress(entity.getData(ModAttachments.HEARTBLOW_COOLDOWN.get())/60.0f);
        }
    }

    private boolean isLegendary(ItemStack stack) {
        return stack.is(ModTags.Items.LEGENDARIES);
    }
}
