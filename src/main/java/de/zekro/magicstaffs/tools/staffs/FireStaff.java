package de.zekro.magicstaffs.tools.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.handlers.SoundHandler;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.util.ConfigEntry;
import de.zekro.magicstaffs.util.Vec3dUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
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
    public void clickAction(World world, EntityPlayer player, EnumHand hand) {
        Random rand = new Random();

        Vec3d aim = Vec3dUtils.multiply(player.getLookVec(), 1);
        Vec3d playerPos = player.getPositionEyes(1);

        if (world.isRemote) {

            for (int i = 0; i < particleAmount; ++i) {
                Vec3d randPos = playerPos.addVector(
                        (rand.nextDouble() - 0.5) / 2 + aim.x,
                        (rand.nextDouble() - 0.5) / 2 - 0.5 + aim.y,
                        (rand.nextDouble() - 0.5) / 2 + aim.z);

                Vec3d randAimVelocity = Vec3dUtils.multiply(aim, new Vec3d(
                        rand.nextFloat() * particleSpread,
                        rand.nextFloat() * particleSpread,
                        rand.nextFloat() * particleSpread));

                world.spawnParticle(
                        EnumParticleTypes.FLAME,
                        randPos.x, randPos.y, randPos.z,
                        randAimVelocity.x, randAimVelocity.y, randAimVelocity.z);
            }
        } else {
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
        }
    }

    @Override
    public Item getEssenceMadeOf() {
        return MagicStaffs.FIRE_ESSENCE;
    }

    @Override
    public List<ConfigEntry> getInitializedConfigEntries() {
        return Arrays.asList(
                new ConfigEntry<>(CONFIG_ENTRY_EFFECTIVE_RANGE, 10, 1, 100, "The range, in blocks, the staff ignites enemies."),
                new ConfigEntry<>(CONFIG_ENTRY_BURN_DURATION, 10, 1, Integer.MAX_VALUE, "The time an ignited enemy is burning."),
                new ConfigEntry<>(CONFIG_ENTRY_PARTICLE_AMOUNT, 100, 1, 1000, "The amount of particles created on each use. (Only cosmetic)"),
                new ConfigEntry<>(CONFIG_ENTRY_PARTICLE_SPREAD, 0.5f, 0f, 1f, "The random particle spread multiplier. (Only cosmetic)")
        );
    }

    @Override
    public void configInitialized() {
        effectiveRange = (int) getConfigEntryByKey(CONFIG_ENTRY_EFFECTIVE_RANGE).getCollected();
        burnDuration = (int) getConfigEntryByKey(CONFIG_ENTRY_BURN_DURATION).getCollected();
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