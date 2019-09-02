package de.zekro.magicstaffs.tools;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IHasModel;
import de.zekro.magicstaffs.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GenericStaff extends ItemBase implements IHasModel {

    private long lastAction;
    private long actionCoolDown = 10;

    public GenericStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
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

    protected void setActionCoolDown(long coolDown) {
        actionCoolDown = coolDown;
    }

    protected boolean checkActionCoolDown(World world) {
        long currTime = world.getTotalWorldTime();
        if (lastAction + actionCoolDown < currTime) {
            lastAction = world.getTotalWorldTime();
            return true;
        }
        return false;
    }
}
