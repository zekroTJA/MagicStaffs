package de.zekro.magicstaffs.blocks.assembler;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.blocks.BlockBase;
import de.zekro.magicstaffs.items.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Assembler extends BlockBase implements IHasModel {

    public Assembler(String name, CreativeTabs tabs) {
        super(name, Material.ROCK, tabs);
        fullBlock = false;
    }

    @Override
    public void registerModels() {
        MagicStaffs.proxy.registerModel(Item.getItemFromBlock(this), 0);
    }
}
