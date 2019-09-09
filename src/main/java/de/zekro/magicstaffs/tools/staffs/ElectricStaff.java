package de.zekro.magicstaffs.tools.staffs;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.handlers.SoundHandler;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.util.ConfigEntry;
import de.zekro.magicstaffs.util.Vec3dUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * Electric Staff Tool Item Class.
 */
public class ElectricStaff extends GenericStaff {

    private final String CONFIG_ENTRY_ACCELERATION = "acceleration";

    private float acceleration = 1.75f;

    /**
     * Create new instance of ElectricStaff.
     * @param name name ID of the item
     * @param tabs creative tab
     */
    public ElectricStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
    }

    @Override
    public void clickAction(World world, EntityPlayer player, EnumHand hand) {
        Vec3d aim = Vec3dUtils.multiply(player.getLookVec(), acceleration);

        if (world.isRemote) {
            player.addVelocity(aim.x, aim.y, aim.z);
        }
        else {
            player.fallDistance = 0F;
        }
    }

    @Override
    public Item getEssenceMadeOf() {
        return MagicStaffs.ELECTRIC_ESSENCE;
    }

    @Override
    public List<ConfigEntry> getInitializedConfigEntries() {
        return Collections.singletonList(
                new ConfigEntry<>(CONFIG_ENTRY_ACCELERATION, 1.75f, 0f, 100f, "The velocity gained by using the staff.")
        );
    }

    @Override
    public void configInitialized() {
        acceleration = (float) getConfigEntryByKey(CONFIG_ENTRY_ACCELERATION).getCollected();

        super.configInitialized();
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        return SoundHandler.ELECTRIC_STAFF_ACTIVATE;
    }
}