package net.chexz.create_quantum.item.custom;

import net.chexz.create_quantum.util.ModTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
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
