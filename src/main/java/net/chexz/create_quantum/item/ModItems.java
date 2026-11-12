package net.chexz.create_quantum.item;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import net.chexz.create_quantum.item.custom.HeartblowItem;
import net.chexz.create_quantum.item.custom.HeavyboltItem;
import net.chexz.create_quantum.item.custom.SacredShotItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.chexz.create_quantum.CreateQuantum;
import net.neoforged.bus.api.IEventBus;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateQuantum.MOD_ID);

    public static final DeferredItem<Item> QUANTUMMODULE = ITEMS.register("quantummodule",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<SequencedAssemblyItem> INCOMPLETEQUANTUMMODULE = ITEMS.register("incompletequantummodule",
            () -> new SequencedAssemblyItem(new Item.Properties()));

    public static final DeferredItem<Item> HEARTBLOW = ITEMS.register("heartblow",
            () -> new HeartblowItem(Tiers.NETHERITE, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 9, -2.4f))));
    public static final DeferredItem<Item> HEAVYBOLT = ITEMS.register("heavybolt",
            () -> new HeavyboltItem(new Item.Properties()));
    public static final DeferredItem<Item> SACREDSHOT = ITEMS.register("sacredshot",
            () -> new SacredShotItem(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
