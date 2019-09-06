package de.zekro.magicstaffs.util;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IEssence;
import de.zekro.magicstaffs.tools.GenericStaff;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    /**
     * Returns a list of staffs extending the
     * GenericStaff class.
     * @return list of staffs
     */
    public static List<GenericStaff> getRegisteredStaffs() {
        ArrayList<GenericStaff> staffs = new ArrayList();
        MagicStaffs.ITEMS
                .stream()
                .filter(item -> item instanceof GenericStaff)
                .forEach(item -> staffs.add((GenericStaff) item));
        return staffs;
    }

    public static List<IEssence> getRegisteredEssences() {
        ArrayList<IEssence> essences = new ArrayList();
        MagicStaffs.ITEMS
                .stream()
                .filter(item -> item instanceof IEssence)
                .forEach(item -> essences.add((IEssence) item));
        return essences;
    }
}
