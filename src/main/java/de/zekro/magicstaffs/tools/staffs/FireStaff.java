package de.zekro.magicstaffs.tools.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.util.ConfigEntry;
import de.zekro.magicstaffs.util.Vec3dUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Electric Staff Tool Item Class.
 */
public class FireStaff extends GenericStaff {

    private final String CONFIG_ENTRY_COOL_DOWN = "cool_down";
    private final String CONFIG_ENTRY_DURABILITY = "durability";
    private final String CONFIG_ENTRY_PARTICLE_AMOUNT = "particle_amount";
    private final String CONFIG_ENTRY_PARTICLE_SPREAD = "particle_spread";
    private final String CONFIG_ENTRY_EFFECTIVE_RANGE = "effective_range";
    private final String CONFIG_ENTRY_BURN_DURATION = "burn_duration";

    private int particleAmount = 100;
    private float particleSpread = 0.5f;
    private int effectiveRange = 10;
    private int burnDuration = 2;

    /**
     * Create new instance of FireStaff.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public FireStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        Random rand = new Random();

        Vec3d aim = Vec3dUtils.multiply(player.getLookVec(), 1);
        Vec3d playerPos = player.getPositionEyes(1);

        if (world.isRemote) {
            if (!coolDownServer.take(world))
                return super.onItemRightClick(world, player, hand);

            // TODO: Play custom sound

            for (int i = 0; i < particleAmount; ++i) {
                Vec3d randPos = playerPos.addVector(
                        rand.nextDouble() - 0.5,
                        rand.nextDouble() - 0.5,
                        rand.nextDouble() - 0.5);

                Vec3d randAimVelocity = Vec3dUtils.multiply(aim, new Vec3d(
                        rand.nextFloat() * particleSpread,
                        rand.nextFloat() * particleSpread,
                        rand.nextFloat() * particleSpread));

                world.spawnParticle(
                        EnumParticleTypes.FLAME,
                        randPos.x, randPos.y, randPos.z,
                        randAimVelocity.x, randAimVelocity.y, randAimVelocity.z);
            }

            if (getMaxDamage(itemStack) <= itemStack.getItemDamage())
                world.playSound(
                    player, player.posX, player.posY, player.posZ,
                    SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS,
                    1F, 1F);
        } else {
            if (!coolDownClient.take(world))
                return super.onItemRightClick(world, player, hand);

            if (!player.isCreative() && getMaxDamage(itemStack) <= itemStack.getItemDamage())
                return new ActionResult<>(EnumActionResult.SUCCESS, ItemStack.EMPTY);

            for (int i = 0; i < effectiveRange; ++i) {
                final Vec3d cPos = new Vec3d(
                        playerPos.x + (aim.x * i),
                        playerPos.y + (aim.y * i),
                        playerPos.z + (aim.z * i));

                final AxisAlignedBB AABB = new AxisAlignedBB(
                        cPos.x - 1, cPos.y - 1, cPos.z - 1,
                        cPos.x + 1, cPos.y + 1, cPos.z + 1);

                if (!world.getBlockState(new BlockPos(cPos.x, cPos.y, cPos.z)).getBlock().equals(Blocks.AIR))
                    break;

                world.getEntitiesInAABBexcluding(player, AABB, entity -> entity instanceof EntityLivingBase)
                        .forEach(entity -> {
                            entity.setFire(burnDuration);
                        });
            }

            setDamage(itemStack, itemStack.getItemDamage() + 1);

            return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
        }

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public Item getEssenceMadeOf() {
        return MagicStaffs.FIRE_ESSENCE;
    }

    @Override
    public List<ConfigEntry> getInitializedConfigEntries() {
        return Arrays.asList(
                new ConfigEntry<>(CONFIG_ENTRY_COOL_DOWN, 5, 0, Integer.MAX_VALUE, "The cool down until the staff can be re used."),
                new ConfigEntry<>(CONFIG_ENTRY_DURABILITY, 64, 0, Integer.MAX_VALUE, "The durability of the staff."),
                new ConfigEntry<>(CONFIG_ENTRY_EFFECTIVE_RANGE, 10, 1, 100, "The range, in blocks, the staff ignites enemies."),
                new ConfigEntry<>(CONFIG_ENTRY_BURN_DURATION, 10, 1, Integer.MAX_VALUE, "The time an ignited enemy is burning."),
                new ConfigEntry<>(CONFIG_ENTRY_PARTICLE_AMOUNT, 100, 1, 1000, "The amount of particles created on each use. (Only cosmetic)"),
                new ConfigEntry<>(CONFIG_ENTRY_PARTICLE_SPREAD, 0.5f, 0f, 10f, "The random particle spread multiplier. (Only cosmetic)")
        );
    }

    @Override
    public void configInitialized() {
        setClientServerCoolDown((int) getConfigEntryByKey(CONFIG_ENTRY_COOL_DOWN).getCollected());
        setMaxDamage((int) getConfigEntryByKey(CONFIG_ENTRY_DURABILITY).getCollected());
        effectiveRange = (int) getConfigEntryByKey(CONFIG_ENTRY_EFFECTIVE_RANGE).getCollected();
        burnDuration = (int) getConfigEntryByKey(CONFIG_ENTRY_BURN_DURATION).getCollected();
        particleAmount = (int) getConfigEntryByKey(CONFIG_ENTRY_PARTICLE_AMOUNT).getCollected();
        particleSpread = (float) getConfigEntryByKey(CONFIG_ENTRY_PARTICLE_SPREAD).getCollected();
    }
}