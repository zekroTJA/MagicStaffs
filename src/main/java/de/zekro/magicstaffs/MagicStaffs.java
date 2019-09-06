package de.zekro.magicstaffs;

import de.zekro.magicstaffs.blocks.infuser.BlockInfuser;
import de.zekro.magicstaffs.creativetab.CreativeTab;
import de.zekro.magicstaffs.handlers.ConfigHandler;
import de.zekro.magicstaffs.handlers.RegistryHandler;
import de.zekro.magicstaffs.items.essences.ElectricEssence;
import de.zekro.magicstaffs.items.staffs.BaseStaff;
import de.zekro.magicstaffs.proxy.CommonProxy;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.tools.staffs.ElectricStaff;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;
import java.util.ArrayList;

/**
 * MagiStaffs Forge Mod.
 * @author zekro Development (Ringo Hoffmann)
 */
@Mod(modid = MagicStaffs.MOD_ID, name = MagicStaffs.NAME, version = MagicStaffs.VERSION)
public class MagicStaffs {

    public static final String MOD_ID = "magicstaffs";
    public static final String NAME = "Magic Staffs";
    public static final String VERSION = "0.1";

    public static ConfigHandler configHandler;

    public static final ArrayList<Item> ITEMS = new ArrayList<>();
    public static final ArrayList<Block> BLOCKS = new ArrayList<>();

    // CREATIVE TABS
    public static final CreativeTab CREATIVE_TAB = new CreativeTab("magic_staffs");

    // ITEMS
    public static final GenericStaff ELECTRIC_STAFF = new ElectricStaff("electric_staff", CREATIVE_TAB);
    public static final Item BASE_STAFF = new BaseStaff("base_staff", CREATIVE_TAB);
    public static final Item ELECTRIC_ESSENCE = new ElectricEssence("electric_essence", CREATIVE_TAB);

    // BLOCKS
    public static final Block INFUSER = new BlockInfuser("infuser", CREATIVE_TAB);

    public MagicStaffs() {
        CREATIVE_TAB.setTabIconItem(new ItemStack(ELECTRIC_STAFF));
    }

    @Mod.Instance(MOD_ID)
    public static MagicStaffs instance;

    @SidedProxy(clientSide = "de.zekro.magicstaffs.proxy.ClientProxy", serverSide = "de.zekro.magicstaffs.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries(event);
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }
}
