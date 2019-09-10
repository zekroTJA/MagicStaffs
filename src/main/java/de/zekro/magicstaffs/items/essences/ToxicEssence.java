package de.zekro.magicstaffs.items.essences;

import de.zekro.magicstaffs.items.IEssence;
import de.zekro.magicstaffs.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Toxic Essence Item class.
 */
public class ToxicEssence extends ItemBase implements IEssence {

    private int rarity = 10;

    /**
     * Create instance of ToxicEssence.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public ToxicEssence(String name, CreativeTabs tabs) {
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
