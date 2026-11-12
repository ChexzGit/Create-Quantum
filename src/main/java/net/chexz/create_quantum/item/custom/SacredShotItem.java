package net.chexz.create_quantum.item.custom;

import net.chexz.create_quantum.networking.ModAttachments;
import net.chexz.create_quantum.util.ModTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.List;

public class SacredShotItem extends BowItem {
    public SacredShotItem(Properties properties) {
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
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            ItemStack itemstack = player.getProjectile(stack);
            if (!itemstack.isEmpty()) {
                int i = this.getUseDuration(stack, entityLiving) - timeLeft;
                i = net.neoforged.neoforge.event.EventHooks.onArrowLoose(stack, level, player, i, !itemstack.isEmpty());
                if (i < 0) return;
                float f = getPowerForTime_(i, entityLiving);
                if (!((double)f < 0.1)) {
                    List<ItemStack> list = draw(stack, itemstack, player);
                    if (level instanceof ServerLevel serverlevel && !list.isEmpty()) {
                        this.shoot(serverlevel, player, player.getUsedItemHand(), new ItemStack(this), list, f * 3.0f, 0, true, null);
                    }
                    level.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public static float getPowerForTime_(int charge, LivingEntity entity) {
            float addedPower = entity.getData(ModAttachments.SACREDSHOT_DAMAGE) + 5;

            float f = (float)charge / 20.0f*addedPower/5f;
            f = (f * f + f * 2.0f) / 3.0f;
            if (f > 1.0f) {
                f = 1.0f;
            }

            return f;
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

    }

    private boolean isLegendary(ItemStack stack) {
        return stack.is(ModTags.Items.LEGENDARIES);
    }
}
