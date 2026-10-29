package net.chexz.create_quantum.item;

import net.chexz.create_quantum.CreateQuantum;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateQuantum.MOD_ID);

    public static final DeferredItem<Item> QUANTUMMODULE = ITEMS.register("quantummodule",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INCOMPLETEQUANTUMMODULE = ITEMS.register("incompletequantummodule",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
