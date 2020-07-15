package de.zekro.magicstaffs.items.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IBaseStaff;
import de.zekro.magicstaffs.items.IHasModel;
import de.zekro.magicstaffs.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Base Staff Item Class.
 */
public class BaseStaff extends ItemBase implements IBaseStaff, IHasModel {

    /**
     * Create new instance of BaseStaff.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public BaseStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
        this.setMaxStackSize(1);
    }

    @Override
    public void registerModels() {
        MagicStaffs.proxy.registerModel(this, 0);
    }
}
