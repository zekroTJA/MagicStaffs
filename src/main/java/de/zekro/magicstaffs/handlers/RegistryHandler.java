package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IHasModel;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Handles automatic registration of items on Forge
 * item and model registration events.
 */
@Mod.EventBusSubscriber
public class RegistryHandler {

    /**
     * Automatically register items on Register&lt;Item&gt; event.
     * @param event register item event
     */
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(MagicStaffs.ITEMS.toArray(new Item[0]));
    }

    /**
     * Automatically register models on ModelRegistryEvent.
     * @param event model registry event
     */
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        MagicStaffs.ITEMS.forEach(item -> {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        });
    }
}
