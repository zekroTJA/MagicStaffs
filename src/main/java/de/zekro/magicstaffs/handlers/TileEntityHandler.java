package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.blocks.infuser.TileEntityInfuser;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityInfuser.class, new ResourceLocation(MagicStaffs.MOD_ID, "infuser"));
    }
}
