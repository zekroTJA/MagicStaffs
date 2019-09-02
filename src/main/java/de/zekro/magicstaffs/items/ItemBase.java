package de.zekro.magicstaffs.items;

import de.zekro.magicstaffs.MagicStaffs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Base class for custom Items which registers unlocalized
 * name, registry name and creative tab. The Item instance
 * is then added to the item registry list.
 */
public class ItemBase extends Item {

    /**
     * Create instance of ItemBase.
     * @param name item name identifier
     */
    public ItemBase(String name) {
        registerItem(name, null);
    }

    /**
     * Create instance of ItemBase.
     * @param name item name identifier
     * @param tabs creative tab instance
     */
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
