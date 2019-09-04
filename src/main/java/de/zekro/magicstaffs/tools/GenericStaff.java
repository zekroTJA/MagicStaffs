package de.zekro.magicstaffs.tools;

import de.zekro.magicstaffs.items.ItemBase;
import de.zekro.magicstaffs.util.CoolDown;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class GenericStaff extends ItemBase {

    private static final long DEFAULT_ACTION_COOL_DOWN = 10;

    protected CoolDown coolDownClient = new CoolDown(DEFAULT_ACTION_COOL_DOWN);
    protected CoolDown coolDownServer = new CoolDown(DEFAULT_ACTION_COOL_DOWN);

    public GenericStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
        setMaxDamage(5);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        return super.onItemRightClick(world, player, hand);
    }

    public abstract Item getEssenceMadeOf();
}