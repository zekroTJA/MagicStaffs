package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.shared.ItemUtils;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Handler registering items which shall be present in
 * loot chests in dungeons.
 */
@Mod.EventBusSubscriber
public class LootTableHandler {

    /**
     * Called when LootTableLoadEvent caused registering Essence
     * items in loot tables of dungeon chests.
     * @param event LootTableLoadEvent
     */
    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        final String path = event.getName().getPath();
        final LootPool pool = event.getTable().getPool("pool2");
        if (pool != null && (path.startsWith("chest") || path.startsWith("entities/witch"))) {
            ItemUtils.getRegisteredEssences().forEach(essence ->
                pool.addEntry(new LootEntryItem(
                        (Item) essence,
                        essence.getRarity(),
                        0,
                        new LootFunction[0],
                        new LootCondition[0],
                        MagicStaffs.MOD_ID + ((Item) essence).getRegistryName().getPath()
                ))
            );
        }
    }

}
