package de.zekro.magicstaffs.items;

import de.zekro.magicstaffs.MagicStaffs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase(String name) {
        registerItem(name, null);
    }

    public ItemBase(String name, CreativeTabs tabs) {
        registerItem(name, tabs);
    }

    private void registerItem(String name, CreativeTabs tabs) {
        setUnlocalizedName(name);
        setRegistryName(name);

        setCreativeTab(tabs != null ? tabs : CreativeTabs.MISC);

        MagicStaffs.ITEMS.add(this);
    }
}
