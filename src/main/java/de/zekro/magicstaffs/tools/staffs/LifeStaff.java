package de.zekro.magicstaffs.tools.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.util.ConfigEntry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Life Staff Tool Item Class.
 */
public class LifeStaff extends GenericStaff {

    private final String CONFIG_ENTRY_PARTICLE_AMOUNT = "particle_amount";
    private final String CONFIG_ENTRY_EFFECTIVE_RANGE_RADIUS = "effective_range_radius";
    private final String CONFIG_ENTRY_HEAL_AMOUNT = "heal_amount";
    private final String CONFIG_ENTRY_EXTINGUISH = "extinguish";
    private final String CONFIG_ENTRY_CURE_BAD_EFFECTS = "cure_bad_effects";

    private int particleAmount = 500;
    private int effectiveRange = 3;
    private int healAmount = 4;
    private boolean extinguish = true;
    private boolean cureBadEffects = true;

    /**
     * Create new instance of LifeStaff.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public LifeStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
    }

    /**
     * Filter function returning true if passed entity is
     * either a living entity which is not a monster or
     * a player.
     * @param entity entity
     * @return true if friendly living entity or player
     */
    private boolean friendlyAndPlayerFilter(Entity entity) {
        if (entity instanceof EntityLivingBase && !entity.isCreatureType(EnumCreatureType.MONSTER, false)) {
            return true;
        }

        return entity instanceof EntityPlayer;
    }

    /**
     * Heal the entity by the configured amount and
     * extinguish the entity if enabled in config.
     * @param entityLiving living entity
     */
    private void doEntityEffect(EntityLivingBase entityLiving) {
        entityLiving.heal(healAmount);

        if (extinguish)
            entityLiving.extinguish();

        if (cureBadEffects) {
            for (PotionEffect effect : entityLiving.getActivePotionEffects()) {
                if (effect.getPotion().isBadEffect())
                    entityLiving.removePotionEffect(effect.getPotion());
            }
        }
    }

    @Override
    public void clickAction(World world, EntityPlayer player, EnumHand hand) {
        Random rand = new Random();

        Vec3d playerPos = player.getPositionEyes(1);

        if (world.isRemote) {

            for (int i = 0; i < particleAmount; ++i) {
                Vec3d randPos = playerPos.addVector(
                        (rand.nextDouble() - 0.5) * 2 * 2 * effectiveRange,
                        rand.nextDouble() * 5 - 2,
                        (rand.nextDouble() - 0.5) * 2 * 2 * effectiveRange);

                world.spawnParticle(
                        EnumParticleTypes.HEART,
                        randPos.x, randPos.y, randPos.z,
                        0, rand.nextFloat(), 0);
            }

        } else {
            final AxisAlignedBB AABB = new AxisAlignedBB(
                    player.posX - effectiveRange, player.posY, player.posZ - effectiveRange,
                    player.posX + effectiveRange, player.posY + 4, player.posZ + effectiveRange);

            doEntityEffect(player);

            world.getEntitiesInAABBexcluding(player, AABB, this::friendlyAndPlayerFilter)
                    .forEach(entity -> doEntityEffect((EntityLivingBase) entity));
        }
    }

    @Override
    public Item getEssenceMadeOf() {
        return MagicStaffs.LIFE_ESSENCE;
    }

    @Override
    public List<ConfigEntry> getInitializedConfigEntries() {
        return Arrays.asList(
                new ConfigEntry<>(CONFIG_ENTRY_EFFECTIVE_RANGE_RADIUS, 3, 1, 100, "The range radius, in blocks, the staff heals friendlies."),
                new ConfigEntry<>(CONFIG_ENTRY_PARTICLE_AMOUNT, 100, 1, 1000, "The amount of particles created on each use. (Only cosmetic)"),
                new ConfigEntry<>(CONFIG_ENTRY_HEAL_AMOUNT, 4, 1, Integer.MAX_VALUE, "The amount of health the friendly is healed."),
                new ConfigEntry<>(CONFIG_ENTRY_EXTINGUISH, true, null, null, "Whether or not to extinguish friendlies."),
                new ConfigEntry<>(CONFIG_ENTRY_CURE_BAD_EFFECTS, true, null, null, "Whether or not to cure bad effects of friendlies.")
        );
    }

    @Override
    public void configInitialized() {
        effectiveRange = (int) getConfigEntryByKey(CONFIG_ENTRY_EFFECTIVE_RANGE_RADIUS).getCollected();
        particleAmount = (int) getConfigEntryByKey(CONFIG_ENTRY_PARTICLE_AMOUNT).getCollected();
        healAmount = (int) getConfigEntryByKey(CONFIG_ENTRY_HEAL_AMOUNT).getCollected();
        extinguish = (boolean) getConfigEntryByKey(CONFIG_ENTRY_EXTINGUISH).getCollected();
        cureBadEffects = (boolean) getConfigEntryByKey(CONFIG_ENTRY_CURE_BAD_EFFECTS).getCollected();

        super.configInitialized();
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        // TODO: add sound
        return null;
    }
}