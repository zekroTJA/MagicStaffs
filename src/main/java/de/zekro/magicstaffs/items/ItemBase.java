package de.zekro.magicstaffs.items;

import de.zekro.magicstaffs.MagicStaffs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Base class for custom Items which registers unlocalized
 * name, registry name and creative tab. The Item instance
 * is then added to the item registry list.
 */
public class ItemBase extends Item implements IHasModel {

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

    /**
     * Register unlocalized name, registry name and
     * set creative tab as same as add item to item
     * registration list.
     * @param name
     * @param tabs
     */
    private void registerItem(String name, CreativeTabs tabs) {
        setRegistryName(MagicStaffs.MOD_ID, name);
        setTranslationKey(name);

        setCreativeTab(tabs != null ? tabs : CreativeTabs.MISC);

        MagicStaffs.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        MagicStaffs.proxy.registerModel(this, 0);
    }
}
