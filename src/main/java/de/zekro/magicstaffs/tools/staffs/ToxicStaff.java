package de.zekro.magicstaffs.tools.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.shared.ConfigEntry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Toxic Staff Tool Item Class.
 */
public class ToxicStaff extends GenericStaff {

    private final String CONFIG_ENTRY_PARTICLE_AMOUNT = "particle_amount";
    private final String CONFIG_ENTRY_EFFECTIVE_RANGE_RADIUS = "effective_range_radius";

    private int particleAmount = 500;
    private int effectiveRange = 3;

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
        Random rand = new Random();

        Vec3d playerPos = player.getPositionEyes(1);

        if (world.isRemote) {

        } else {

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
                new ConfigEntry<>(CONFIG_ENTRY_PARTICLE_AMOUNT, 100, 1, 1000, "The amount of particles created on each use. (Only cosmetic)")
        );
    }

    @Override
    public void configInitialized() {
        effectiveRange = (int) getConfigEntryByKey(CONFIG_ENTRY_EFFECTIVE_RANGE_RADIUS).getCollected();
        particleAmount = (int) getConfigEntryByKey(CONFIG_ENTRY_PARTICLE_AMOUNT).getCollected();

        super.configInitialized();
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        // TODO: add sound
        return null;
    }
}