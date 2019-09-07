package de.zekro.magicstaffs.tools;

import de.zekro.magicstaffs.items.ItemBase;
import de.zekro.magicstaffs.util.ConfigEntry;
import de.zekro.magicstaffs.util.CoolDown;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generic Staff Abstract Class.
 * Defines the general structure of a Staff Tool Item Class.
 */
public abstract class GenericStaff extends ItemBase {

    private static final long DEFAULT_ACTION_COOL_DOWN = 10;

    private final String CONFIG_ENTRY_DURABILITY = "durability";
    private final String CONFIG_ENTRY_COOL_DOWN = "cool_down";

    protected CoolDown coolDownClient = new CoolDown(DEFAULT_ACTION_COOL_DOWN);
    protected CoolDown coolDownServer = new CoolDown(DEFAULT_ACTION_COOL_DOWN);

    private List<ConfigEntry> configEntries = new ArrayList<>(Arrays.asList(
            new ConfigEntry<>(CONFIG_ENTRY_COOL_DOWN, 5, 0, Integer.MAX_VALUE, "The cool down until the staff can be re used."),
            new ConfigEntry<>(CONFIG_ENTRY_DURABILITY, 64, 0, Integer.MAX_VALUE, "The durability of the staff.")
    ));

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

        configEntries.addAll(getInitializedConfigEntries());
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
    public void configInitialized() {
        setClientServerCoolDown((int) getConfigEntryByKey(CONFIG_ENTRY_COOL_DOWN).getCollected());
        setMaxDamage((int) getConfigEntryByKey(CONFIG_ENTRY_DURABILITY).getCollected());
    }

    /**
     * Action performed on item right click when not on cool down.
     * This will be called twice, once for the server and once for
     * the client world.
     * @param world world in
     * @param player player used item
     * @param hand hand in which item was used
     */
    public abstract void clickAction(World world, EntityPlayer player, EnumHand hand);

    /**
     * Returns the list of ConfigEntries.
     * @return list of config entries
     */
    public List<ConfigEntry> getConfigEntries() {
        return configEntries;
    }

    /**
     * Sets the cool down values for the client and the
     * server cool down handlers.
     * @param coolDown cool down in world ticks
     */
    public void setClientServerCoolDown(long coolDown) {
        coolDownClient.setCoolDown(coolDown);
        coolDownServer.setCoolDown(coolDown);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);

        if (world.isRemote) {
            if (!coolDownServer.take(world))
                return super.onItemRightClick(world, player, hand);

            // TODO: Play custom sound

            if (getMaxDamage(itemStack) <= itemStack.getItemDamage())
                world.playSound(
                        player, player.posX, player.posY, player.posZ,
                        SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS,
                        1F, 1F);
        } else {
            if (!coolDownClient.take(world))
                return super.onItemRightClick(world, player, hand);

            if (!player.isCreative()) {
                setDamage(itemStack, itemStack.getItemDamage() + 1);

                if (getMaxDamage(itemStack) <= itemStack.getItemDamage())
                    return new ActionResult<>(EnumActionResult.SUCCESS, ItemStack.EMPTY);
            }
        }

        clickAction(world, player, hand);

        return super.onItemRightClick(world, player, hand);
    }

    public ConfigEntry getConfigEntryByKey(String key) throws NullPointerException {
        return configEntries
                .stream()
                .filter(e -> e.getKey().equals(key))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }
}