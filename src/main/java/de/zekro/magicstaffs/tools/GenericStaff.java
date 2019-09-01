package de.zekro.magicstaffs.tools;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IHasModel;
import de.zekro.magicstaffs.items.ItemBase;
import de.zekro.magicstaffs.items.ToolItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class GenericStaff extends ItemBase implements IHasModel {

    public GenericStaff(String name) {
        super(name);
        setMaxDamage(5);
        setMaxStackSize(1);
    }

    @Override
    public void registerModels() {
        MagicStaffs.proxy.registerModel(this, 0);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        return super.onItemRightClick(world, player, hand);
    }
}
