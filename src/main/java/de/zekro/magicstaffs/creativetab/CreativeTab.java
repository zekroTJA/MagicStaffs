package de.zekro.magicstaffs.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * Custom creative tabs.
 */
public class CreativeTab extends CreativeTabs {
    private ItemStack tabIconItem;

    /**
     * Create custom creative tab instance with given ID.
     * @param name Name identifier of the tab.
     */
    public CreativeTab(String name) {
        super(name);
    }

    @Override
    public ItemStack createIcon() {
        return tabIconItem;
    }

    /**
     * Returns the item stack used as creative tab icon.
     * @return tab icon item stack
     */
    public ItemStack getTabIconItem() {
        return tabIconItem;
    }

    /**
     * Sets the tab icon item stack for the creative tab.
     * @param iconItem tab icon stack
     */
    public void setTabIconItem(ItemStack iconItem) {
        tabIconItem = iconItem;
    }
}
