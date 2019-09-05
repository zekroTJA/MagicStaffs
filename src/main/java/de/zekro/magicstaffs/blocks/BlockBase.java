package de.zekro.magicstaffs.blocks;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/**
 * Base class for generic blocks.
 */
public class BlockBase extends Block implements IHasModel {

    /**
     * Create new base block instance.
     * Registers material, unlocalized name and registry name
     * as same as the creative tab.
     * Also, the block instance is added to the block registry
     * and the item of the block to the item registry.
     * @param name name ID of the block
     * @param material material of the block
     * @param tabs creative tab
     */
    public BlockBase(String name, Material material, CreativeTabs tabs) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);

        setCreativeTab(tabs != null ? tabs : CreativeTabs.MISC);

        MagicStaffs.BLOCKS.add(this);
        MagicStaffs.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        MagicStaffs.proxy.registerModel(Item.getItemFromBlock(this), 0);
    }
}
