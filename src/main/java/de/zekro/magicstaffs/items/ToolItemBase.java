package de.zekro.magicstaffs.items;

import com.google.common.collect.Sets;
import de.zekro.magicstaffs.MagicStaffs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemTool;

public class ToolItemBase extends ItemTool {

    public ToolItemBase(String name, CreativeTabs tabs, ToolMaterial material) {
        super(material, Sets.newHashSet());
        registerItem(name, tabs);
    }

    public ToolItemBase(String name, ToolMaterial material) {
        super(material, Sets.newHashSet());
        registerItem(name, null);
    }

    private void registerItem(String name, CreativeTabs tabs) {
        setUnlocalizedName(name);
        setRegistryName(name);

        setCreativeTab(tabs != null ? tabs : CreativeTabs.MISC);

        MagicStaffs.ITEMS.add(this);
    }

}
