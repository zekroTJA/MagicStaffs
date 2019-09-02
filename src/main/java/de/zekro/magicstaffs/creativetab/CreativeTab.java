package de.zekro.magicstaffs.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {
    private ItemStack tabIconItem;

    public CreativeTab(String name) {
        super(name);
    }

    public ItemStack getTabIconItem() {
        return tabIconItem;
    }

    public CreativeTab setTabIconItem(ItemStack iconItem) {
        tabIconItem = iconItem;
        return this;
    }
}
