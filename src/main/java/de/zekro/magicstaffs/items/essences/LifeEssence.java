package de.zekro.magicstaffs.items.essences;

import de.zekro.magicstaffs.items.IEssence;
import de.zekro.magicstaffs.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Life Essence Item class.
 */
public class LifeEssence extends ItemBase implements IEssence {

    private int rarity = 10;

    /**
     * Create instance of LifeEssence.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public LifeEssence(String name, CreativeTabs tabs) {
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
