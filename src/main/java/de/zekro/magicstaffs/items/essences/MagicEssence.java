package de.zekro.magicstaffs.items.essences;

import de.zekro.magicstaffs.items.IEssence;
import de.zekro.magicstaffs.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Magic Essence Item class.
 */
public class MagicEssence extends ItemBase implements IEssence {

    private int rarity = 0;

    /**
     * Create instance of MagicEssence.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public MagicEssence(String name, CreativeTabs tabs) {
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
