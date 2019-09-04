package de.zekro.magicstaffs.items.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IBaseStaff;
import de.zekro.magicstaffs.items.IHasModel;
import de.zekro.magicstaffs.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;

public class BaseStaff extends ItemBase implements IBaseStaff, IHasModel {

    public BaseStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
        // TODO: Set stackSize(1)
//        setMaxStackSize(1);
    }

    @Override
    public void registerModels() {
        MagicStaffs.proxy.registerModel(this, 0);
    }
}
