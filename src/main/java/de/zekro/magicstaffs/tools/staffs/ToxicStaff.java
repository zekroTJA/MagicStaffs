package de.zekro.magicstaffs.tools.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.handlers.SoundHandler;
import de.zekro.magicstaffs.shared.StaffUtil;
import de.zekro.magicstaffs.shared.Vec3dUtils;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.shared.ConfigEntry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Toxic Staff Tool Item Class.
 */
public class ToxicStaff extends GenericStaff {

    private final String CONFIG_ENTRY_PARTICLE_AMOUNT = "particle_amount";
    private final String CONFIG_ENTRY_EFFECTIVE_RANGE = "effective_range";
    private final String CONFIG_ENTRY_DURATION = "effect_duration";

    private int particleAmount = 500;
    private int effectiveRange = 6;
    private int duration = 10;

    /**
     * Create new instance of ToxicStaff.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public ToxicStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
    }

    @Override
    public void clickAction(World world, EntityPlayer player, EnumHand hand) {
        final Random rand = new Random();

        final Vec3d aim = Vec3dUtils.multiply(player.getLookVec(), 1);
        final Vec3d playerPos = player.getPositionEyes(1);

        StaffUtil.getEntitiesInAimDirection(effectiveRange, player, world, entity -> entity instanceof EntityLivingBase)
            .forEach(entity -> {
                if (world.isRemote) {
                    for (int i = 0; i < particleAmount; ++i) {
                        Vec3d randPos = entity.getPositionVector().add(
                                (rand.nextDouble() - 0.5) * 2,
                                (rand.nextDouble() - 0.5 + 1) * 2,
                                (rand.nextDouble() - 0.5) * 2);

                        world.spawnParticle(
                                EnumParticleTypes.SPELL_MOB,
                                randPos.x, randPos.y, randPos.z,
                                0, rand.nextDouble(), 0);
                    }
                } else {
                    ((EntityLivingBase) entity).addPotionEffect(
                            new PotionEffect(Objects.requireNonNull(Potion.getPotionById(19)), duration * 20));
                }
            });
    }

    @Override
    public Item getEssenceMadeOf() {
        return MagicStaffs.TOXIC_ESSENCE;
    }

    @Override
    public List<ConfigEntry> getInitializedConfigEntries() {
        return Arrays.asList(
                new ConfigEntry<>(CONFIG_ENTRY_EFFECTIVE_RANGE, 6, 1, 100, "The range radius, in blocks, the staff heals friendlies."),
                new ConfigEntry<>(CONFIG_ENTRY_PARTICLE_AMOUNT, 100, 1, 1000, "The amount of particles created on each use. (Only cosmetic)"),
                new ConfigEntry<>(CONFIG_ENTRY_DURATION, 10, 0, Integer.MAX_VALUE, "The duration the effect consists.")
        );
    }

    @Override
    public void configInitialized() {
        effectiveRange = (int) getConfigEntryByKey(CONFIG_ENTRY_EFFECTIVE_RANGE).getCollected();
        particleAmount = (int) getConfigEntryByKey(CONFIG_ENTRY_PARTICLE_AMOUNT).getCollected();
        duration = (int) getConfigEntryByKey(CONFIG_ENTRY_DURATION).getCollected();

        super.configInitialized();
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        return SoundHandler.TOXIC_STAFF_ACTIVATE;
    }
}