package de.zekro.magicstaffs;

import de.zekro.magicstaffs.blocks.infuser.BlockInfuser;
import de.zekro.magicstaffs.creativetab.CreativeTab;
import de.zekro.magicstaffs.handlers.ConfigHandler;
import de.zekro.magicstaffs.handlers.RegistryHandler;
import de.zekro.magicstaffs.handlers.SoundHandler;
import de.zekro.magicstaffs.items.essences.*;
import de.zekro.magicstaffs.items.staffs.BaseStaff;
import de.zekro.magicstaffs.proxy.CommonProxy;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.tools.staffs.ElectricStaff;
import de.zekro.magicstaffs.tools.staffs.FireStaff;
import de.zekro.magicstaffs.tools.staffs.LifeStaff;
import de.zekro.magicstaffs.tools.staffs.ToxicStaff;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.ArrayList;

/**
 * MagiStaffs Forge Mod.
 * @author zekro Development (Ringo Hoffmann)
 */
@Mod(modid = MagicStaffs.MOD_ID, name = MagicStaffs.NAME, version = MagicStaffs.VERSION)
public class MagicStaffs {

    // General Mod Properties
    public static final String MOD_ID = "magicstaffs";
    public static final String NAME = "Magic Staffs";
    public static final String VERSION = "0.8";

    public static ConfigHandler configHandler;

    public static final ArrayList<Item> ITEMS = new ArrayList<>();
    public static final ArrayList<Block> BLOCKS = new ArrayList<>();

    // CREATIVE TABS
    public static final CreativeTab CREATIVE_TAB = new CreativeTab("magic_staffs");

    // ESSENCES
    public static final Item MAGIC_ESSENCE = new MagicEssence("magic_essence", CREATIVE_TAB);
    public static final Item ELECTRIC_ESSENCE = new ElectricEssence("electric_essence", CREATIVE_TAB);
    public static final Item FIRE_ESSENCE = new FireEssence("fire_essence", CREATIVE_TAB);
    public static final Item LIFE_ESSENCE = new LifeEssence("life_essence", CREATIVE_TAB);
    public static final Item TOXIC_ESSENCE = new ToxicEssence("toxic_essence", CREATIVE_TAB);

    // STAFFS
    public static final Item BASE_STAFF = new BaseStaff("base_staff", CREATIVE_TAB);
    public static final GenericStaff ELECTRIC_STAFF = new ElectricStaff("electric_staff", CREATIVE_TAB);
    public static final GenericStaff FIRE_STAFF = new FireStaff("fire_staff", CREATIVE_TAB);
    public static final GenericStaff LIFE_STAFF = new LifeStaff("life_staff", CREATIVE_TAB);
    public static final GenericStaff TOXIC_STAFF = new ToxicStaff("toxic_staff", CREATIVE_TAB);

    // BLOCKS
    public static final Block INFUSER = new BlockInfuser("infuser", CREATIVE_TAB);

    /**
     * Main class initialization.
     */
    public MagicStaffs() {
        CREATIVE_TAB.setTabIconItem(new ItemStack(ELECTRIC_STAFF));
    }

    // Mod instance.
    @Mod.Instance(MOD_ID)
    public static MagicStaffs instance;

    // Proxy depending on side called from.
    @SidedProxy(clientSide = "de.zekro.magicstaffs.proxy.ClientProxy", serverSide = "de.zekro.magicstaffs.proxy.CommonProxy")
    public static CommonProxy proxy;

    // Pre-init event handler
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        SoundHandler.registerSounds();
    }

    // Server-init event handler
    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }
}
