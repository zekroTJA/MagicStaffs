package de.zekro.magicstaffs.tools.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.util.Vec3dUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Electric Staff Tool Item Class.
 */
public class ElectricStaff extends GenericStaff {

    private static final double ACCELERATION = 1.75;
    private static final int MAX_DAMAGE = 64;

    /**
     * Create new instance of ElectricStaff.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public ElectricStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
        setMaxDamage(MAX_DAMAGE);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        Vec3d aim = Vec3dUtils.multiply(player.getLookVec(), ACCELERATION);

        if (world.isRemote) {
            if (!coolDownServer.take(world))
                return super.onItemRightClick(world, player, hand);

            // TODO: Play custom sound
            player.addVelocity(aim.x, aim.y, aim.z);

            if (getMaxDamage(itemStack) == itemStack.getItemDamage())
                world.playSound(
                    player, player.posX, player.posY, player.posZ,
                    SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS,
                    1F, 1F);
        } else {
            if (player.isCreative() || !coolDownClient.take(world))
                return super.onItemRightClick(world, player, hand);

            if (getMaxDamage(itemStack) == itemStack.getItemDamage())
                return new ActionResult<>(EnumActionResult.SUCCESS, ItemStack.EMPTY);

            player.fallDistance = 0F;
            setDamage(itemStack, itemStack.getItemDamage() + 1);

            return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
        }

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public Item getEssenceMadeOf() {
        return MagicStaffs.ELECTRIC_ESSENCE;
    }
}