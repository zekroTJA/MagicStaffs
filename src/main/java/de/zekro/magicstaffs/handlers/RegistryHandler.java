package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Handles automatic registration of items on Forge
 * item and model registration events.
 */
@Mod.EventBusSubscriber
public class RegistryHandler {

    public static void initRegistries() {
        NetworkRegistry.INSTANCE.registerGuiHandler(MagicStaffs.instance, new GuiHandler());
    }

    /**
     * Automatically register items on Register&lt;Item&gt; event.
     * @param event register item event
     */
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(MagicStaffs.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(MagicStaffs.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
    }

    /**
     * Automatically register models on ModelRegistryEvent.
     * @param event model registry event
     */
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        MagicStaffs.ITEMS
                .stream()
                .filter(item -> item instanceof IHasModel)
                .forEach(item -> ((IHasModel) item).registerModels());

        MagicStaffs.BLOCKS
                .stream()
                .filter(item -> item instanceof IHasModel)
                .forEach(item -> ((IHasModel) item).registerModels());
    }
}
