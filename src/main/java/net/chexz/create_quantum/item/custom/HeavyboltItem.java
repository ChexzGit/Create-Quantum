package net.chexz.create_quantum.item.custom;

import net.chexz.create_quantum.networking.ModAttachments;
import net.chexz.create_quantum.networking.packet.UpdateCooldownC2S;
import net.chexz.create_quantum.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public class HeavyboltItem extends MaceItem {
    public ServerBossEvent barMain =
            new ServerBossEvent(Component.literal("Heavybolt Main Cooldown"),
                    BossEvent.BossBarColor.BLUE,
                    BossEvent.BossBarOverlay.NOTCHED_20
            );

    public HeavyboltItem(Properties properties) {
        super(properties);
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
    public float getAttackDamageBonus(Entity target, float damage, DamageSource damageSource) {
        if (damageSource.getDirectEntity() instanceof LivingEntity livingentity) {
            if (!canSmashAttack(livingentity)) {
                return 0.0F;
            } else {
                float f3 = 3.0F;
                float f = 8.0F;
                float f1 = livingentity.fallDistance;
                float f2;
                if (f1 <= 3.0F) {
                    f2 = 4.0F * f1;
                } else if (f1 <= 8.0F) {
                    f2 = 12.0F + 2.0F * (f1 - 3.0F);
                } else {
                    f2 = 22.0F + f1 - 8.0F;
                }

                return livingentity.level() instanceof ServerLevel serverlevel
                        ? f2 * 1.5f * f1
                        : f2;
            }
        } else {
            return 0.0F;
        }
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

            barMain.setProgress(entity.getData(ModAttachments.HEAVYBOLT_COOLDOWN.get())/220.0f);
        }
    }

    private boolean isLegendary(ItemStack stack) {
        return stack.is(ModTags.Items.LEGENDARIES);
    }
}
