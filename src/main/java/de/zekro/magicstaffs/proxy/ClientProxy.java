package de.zekro.magicstaffs.proxy;

import de.zekro.magicstaffs.blocks.infuser.TileEntityInfuser;
import de.zekro.magicstaffs.blocks.infuser.TileEntityInfuserRenderer;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * Client proxy class.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerModel(Item item, int metadata)
    {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfuser.class, new TileEntityInfuserRenderer());
    }
}