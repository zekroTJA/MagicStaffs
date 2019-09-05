package de.zekro.magicstaffs.items.essences;

import de.zekro.magicstaffs.items.IEssence;
import de.zekro.magicstaffs.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Electric Essence Item class.
 */
public class ElectricEssence extends ItemBase implements IEssence {

    /**
     * Create instance of ElectricEssence.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public ElectricEssence(String name, CreativeTabs tabs) {
        super(name, tabs);
    }
}
