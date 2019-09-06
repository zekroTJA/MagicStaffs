package de.zekro.magicstaffs.items.essences;

import de.zekro.magicstaffs.items.IEssence;
import de.zekro.magicstaffs.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Electric Essence Item class.
 */
public class FireEssence extends ItemBase implements IEssence {

    private int rarity;

    /**
     * Create instance of ElectricEssence.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public FireEssence(String name, CreativeTabs tabs) {
        super(name, tabs);
    }

    @Override
    public int getRarity() {
        return rarity;
    }

    @Override
    public void setRarity(int rarity) {
        this.rarity = rarity;
    }
}
