package de.zekro.magicstaffs.tools;

import de.zekro.magicstaffs.items.ItemBase;
import de.zekro.magicstaffs.util.ConfigEntry;
import de.zekro.magicstaffs.util.CoolDown;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

/**
 * Generic Staff Abstract Class.
 * Defines the general structure of a Staff Tool Item Class.
 */
public abstract class GenericStaff extends ItemBase {

    private static final long DEFAULT_ACTION_COOL_DOWN = 10;

    protected CoolDown coolDownClient = new CoolDown(DEFAULT_ACTION_COOL_DOWN);
    protected CoolDown coolDownServer = new CoolDown(DEFAULT_ACTION_COOL_DOWN);

    protected List<ConfigEntry> configEntries;

    /**
     * Create new instance of GenericStaff.
     * Creates item instance and sets default
     * max damage (64) and max stack size (1).
     * @param name name ID of the staff
     * @param tabs creative tab
     */
    public GenericStaff(String name, CreativeTabs tabs) {
        super(name, tabs);
        setMaxDamage(64);
        setMaxStackSize(1);

        configEntries = getInitializedConfigEntries();
    }

    /**
     * Returns the essence the Staff is made of.
     * @return Essence item instance
     */
    public abstract Item getEssenceMadeOf();

    /**
     * Returns a list of ConfigEntries which will be added
     * to the configs and set to the staffs ConfigEntries
     * list.
     * @return list of ConfigEntries
     */
    protected abstract List<ConfigEntry> getInitializedConfigEntries();

    /**
     * Runs when all configurations were read from file and set
     * to initialized ConfigEntries.
     */
    public abstract void configInitialized();

    /**
     * Returns the list of ConfigEntries.
     * @return list of config entries
     */
    public List<ConfigEntry> getConfigEntries() {
        return configEntries;
    };

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        return super.onItemRightClick(world, player, hand);
    }

    @Nullable
    public ConfigEntry getConfigEntryByKey(String key) {
        return configEntries
                .stream()
                .filter(e -> e.getKey() == key)
                .findFirst()
                .orElse(null);
    }
}