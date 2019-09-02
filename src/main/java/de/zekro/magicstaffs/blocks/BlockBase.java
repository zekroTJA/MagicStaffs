package de.zekro.magicstaffs.blocks;

import de.zekro.magicstaffs.MagicStaffs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {

    public BlockBase(String name, Material material, CreativeTabs tabs) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);

        setCreativeTab(tabs != null ? tabs : CreativeTabs.MISC);

        MagicStaffs.BLOCKS.add(this);
        MagicStaffs.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
