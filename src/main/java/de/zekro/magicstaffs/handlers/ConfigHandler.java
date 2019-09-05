package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.tools.GenericStaff;
import net.minecraft.util.Tuple;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Handler managing mod configuration initialization.
 */
public class ConfigHandler {

    public static int GUI_INFUSER = 0;

    /**
     * Initialize configuration instances for main configuration
     * and staff configurations.
     * @param mainConfigFile main config file handler
     * @param staffPropertyConfigs tuple list of staff configuration instances
     */
    private static void init(File mainConfigFile, List<Tuple<GenericStaff, File>> staffPropertyConfigs) {
        Configuration mainConfig = new Configuration(mainConfigFile);

        String category;

        // IDS
        category = "ids";
        mainConfig.addCustomCategoryComment(category, "Set registry IDs for GUI.");
        GUI_INFUSER = mainConfig.getInt(
                "infusion_table_main", category, 0, 0, Integer.MAX_VALUE,
                "ID for the Infusion Table main GUI."
        );

        staffPropertyConfigs.forEach(tp ->
                initStaffConfig(tp.getSecond(), tp.getFirst()));

        mainConfig.save();
    }

    /**
     * Initialize staff configuration for a staff item instance
     * and set configuration values to staff.
     * @param file configuration file handler
     * @param staff staff item instance
     */
    private static void initStaffConfig(File file, GenericStaff staff) {
        final String cat = "properties";
        Configuration cfg = new Configuration(file);

        cfg.addCustomCategoryComment(cat, staff.getUnlocalizedName() + " staff properties.");

        staff.getConfigEntries().forEach(entry -> {
            if (entry.getDef() instanceof Integer) {
                entry.setCollected(
                        cfg.getInt(entry.getKey(), cat, (int) entry.getDef(),
                        (int) entry.getMin(), (int) entry.getMax(), entry.getComment())
                );
            } else if (entry.getDef() instanceof Double || entry.getDef() instanceof Float) {
                entry.setCollected(
                        cfg.getFloat(entry.getKey(), cat, (float) entry.getDef(),
                                (float) entry.getMin(), (float) entry.getMax(), entry.getComment())
                );
            }
            staff.configInitialized();
        });

        cfg.save();
    }

    /**
     * Returns a list of staffs extending the
     * GenericStaff class.
     * @return list of staffs
     */
    private static List<GenericStaff> getRegisteredStaffs() {
        ArrayList<GenericStaff> staffs = new ArrayList();
        MagicStaffs.ITEMS
                .stream()
                .filter(item -> item instanceof GenericStaff)
                .forEach(item -> staffs.add((GenericStaff) item));
        return staffs;
    }

    /**
     * Create config locations and initialize
     * main config and staff configurations.
     * @param event
     */
    public static void registerConfig(FMLPreInitializationEvent event) {
        File mainConfigLocation = new File(event.getModConfigurationDirectory() + "/" + MagicStaffs.MOD_ID);
        File staffsConfigLocation = new File(mainConfigLocation.getPath() + "/staff_properties");

        mainConfigLocation.mkdirs();
        staffsConfigLocation.mkdirs();

        ArrayList<Tuple<GenericStaff, File>> staffPropertyFiles = new ArrayList<>();

        getRegisteredStaffs()
                .forEach(staff -> staffPropertyFiles.add(
                        new Tuple<>(staff, new File(staffsConfigLocation.getPath(), staff.getRegistryName().getResourcePath() + ".cfg"))));

        init(
                new File(mainConfigLocation.getPath(), MagicStaffs.MOD_ID + ".cfg"),
                staffPropertyFiles
        );
    }
}
