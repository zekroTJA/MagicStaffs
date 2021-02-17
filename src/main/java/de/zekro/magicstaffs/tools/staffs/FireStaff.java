package de.zekro.magicstaffs.tools.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.handlers.SoundHandler;
import de.zekro.magicstaffs.shared.StaffUtil;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.shared.ConfigEntry;
import de.zekro.magicstaffs.shared.Vec3dUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Fire Staff Tool Item Class.
 */
public class FireStaff extends GenericStaff {

    private final String CONFIG_ENTRY_PARTICLE_AMOUNT = "particle_amount";
    private final String CONFIG_ENTRY_PARTICLE_SPREAD = "particle_spread";
    private final String CONFIG_ENTRY_EFFECTIVE_RANGE = "effective_range";
    private final String CONFIG_ENTRY_BURN_DURATION = "burn_duration";
    private final String CONFIG_ENTRY_IGNITE_BLOCKS = "ignite_blocks";

    private int particleAmount = 100;
    private float particleSpread = 0.5f;
    private int effectiveRange = 6;
    private int burnDuration = 2;
    private boolean igniteBlocks = true;

    /**
     * Create new instance of FireStaff.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public FireStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
    }

    @Override
    public void clickAction(World world, EntityPlayer player, EnumHand hand) {
        final Random rand = new Random();

        final Vec3d aim = Vec3dUtils.multiply(player.getLookVec(), 1);
        final Vec3d playerPos = player.getPositionEyes(1);

        if (world.isRemote) {

            for (int i = 0; i < particleAmount; ++i) {
                final Vec3d randPos = playerPos.add(
                        (rand.nextDouble() - 0.5) / 2 + aim.x,
                        (rand.nextDouble() - 0.5) / 2 - 0.5 + aim.y,
                        (rand.nextDouble() - 0.5) / 2 + aim.z);

                final Vec3d randAimVelocity = Vec3dUtils.multiply(aim, new Vec3d(
                        rand.nextFloat() * particleSpread,
                        rand.nextFloat() * particleSpread,
                        rand.nextFloat() * particleSpread));

                world.spawnParticle(
                        EnumParticleTypes.FLAME,
                        randPos.x, randPos.y, randPos.z,
                        randAimVelocity.x, randAimVelocity.y, randAimVelocity.z);
            }

        } else {
            StaffUtil.getEntitiesInAimDirection(effectiveRange, player, world, entity -> entity instanceof EntityLivingBase)
                    .forEach(entity ->
                        entity.setFire(burnDuration));


            if (igniteBlocks) {
                StaffUtil.iterateLinearAimDirection(effectiveRange, player, (vec) -> {

                    final BlockPos pos = new BlockPos(vec.x, vec.y, vec.z);
                    if (world.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
                        world.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
                        return true;
                    }

                    return false;
                });
            }
        }
    }

    @Override
    public Item getEssenceMadeOf() {
        return MagicStaffs.FIRE_ESSENCE;
    }

    @Override
    public List<ConfigEntry> getInitializedConfigEntries() {
        return Arrays.asList(
                new ConfigEntry<>(CONFIG_ENTRY_EFFECTIVE_RANGE, 6, 1, 100, "The range, in blocks, the staff ignites enemies."),
                new ConfigEntry<>(CONFIG_ENTRY_BURN_DURATION, 10, 1, Integer.MAX_VALUE, "The time an ignited enemy is burning."),
                new ConfigEntry<>(CONFIG_ENTRY_IGNITE_BLOCKS, true, "Whether or not to ignite blocks in range."),
                new ConfigEntry<>(CONFIG_ENTRY_PARTICLE_AMOUNT, 100, 1, 1000, "The amount of particles created on each use. (Only cosmetic)"),
                new ConfigEntry<>(CONFIG_ENTRY_PARTICLE_SPREAD, 0.5f, 0f, 1f, "The random particle spread multiplier. (Only cosmetic)")
        );
    }

    @Override
    public void configInitialized() {
        effectiveRange = (int) getConfigEntryByKey(CONFIG_ENTRY_EFFECTIVE_RANGE).getCollected();
        burnDuration = (int) getConfigEntryByKey(CONFIG_ENTRY_BURN_DURATION).getCollected();
        igniteBlocks = (boolean) getConfigEntryByKey(CONFIG_ENTRY_IGNITE_BLOCKS).getCollected();
        particleAmount = (int) getConfigEntryByKey(CONFIG_ENTRY_PARTICLE_AMOUNT).getCollected();
        particleSpread = (float) getConfigEntryByKey(CONFIG_ENTRY_PARTICLE_SPREAD).getCollected();

        super.configInitialized();
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        return SoundHandler.FIRE_STAFF_ACTIVATE;
    }
}