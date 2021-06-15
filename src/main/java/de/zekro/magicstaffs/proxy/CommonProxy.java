package de.zekro.magicstaffs.proxy;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.blocks.infuser.TileEntityInfuser;

import net.minecraft.item.Item;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Common proxy class.
 */
public class CommonProxy {
    /**
     * Register item model.
     * @param item Item instance
     * @param metadata item metadata
     */
    public void registerModel(Item item, int metadata) {
        GameRegistry.registerTileEntity(TileEntityInfuser.class, new ResourceLocation(MagicStaffs.MOD_ID, "infuser"));
    }
}
