package de.zekro.magicstaffs.blocks;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {

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
