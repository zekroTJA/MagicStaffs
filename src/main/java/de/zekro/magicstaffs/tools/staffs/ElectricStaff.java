package de.zekro.magicstaffs.tools.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IHasModel;
import de.zekro.magicstaffs.items.ItemBase;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.util.Vec3dUtils;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ElectricStaff extends GenericStaff {

    private static final double ACCELERATION = 1.75;

    public ElectricStaff(String name) {
        super(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemInstance = player.getHeldItem(hand);
        Vec3d aim = Vec3dUtils.multiply(player.getLookVec(), ACCELERATION);

        // TODO: Play custom sound
        player.addVelocity(aim.x, aim.y, aim.z);

        if (!player.isCreative()) {
            player.fallDistance = 0;

            if (itemInstance.getMaxDamage() == itemInstance.getItemDamage()) {
                player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1, 1);
                return new ActionResult<>(EnumActionResult.PASS, ItemStack.EMPTY);
            }

            itemInstance.setItemDamage(itemInstance.getItemDamage() + 1);
        }

        return super.onItemRightClick(world, player, hand);
    }
}
