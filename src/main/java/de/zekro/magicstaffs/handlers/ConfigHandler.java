package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.shared.ItemUtils;
import net.minecraft.item.Item;
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

    public int guiIDInfuser = 0;
    public boolean hotReloadActive = false;

    File mainConfigFile;
    List<Tuple<GenericStaff, File>> staffPropertyConfigs;

    public ConfigHandler(FMLPreInitializationEvent event) {
        File mainConfigLocation = new File(event.getModConfigurationDirectory() + "/" + MagicStaffs.MOD_ID);
        File staffsConfigLocation = new File(mainConfigLocation.getPath() + "/staff_properties");

        mainConfigLocation.mkdirs();
        staffsConfigLocation.mkdirs();

        ArrayList<Tuple<GenericStaff, File>> staffPropertyFiles = new ArrayList<>();

        ItemUtils.getRegisteredStaffs()
                .forEach(staff -> staffPropertyFiles.add(
                        new Tuple<>(staff, new File(staffsConfigLocation.getPath(), staff.getRegistryName().getPath() + ".cfg"))));

        mainConfigFile = new File(mainConfigLocation.getPath(), MagicStaffs.MOD_ID + ".cfg");
        staffPropertyConfigs = staffPropertyFiles;

        init();
    }

    /**
     * Initialize configuration instances for main configuration
     * and staff configurations.
     */
    public void init() {
        Configuration mainConfig = new Configuration(mainConfigFile);

        String category;

        // IDS
        category = "ids";
        mainConfig.addCustomCategoryComment(category, "Set registry IDs for GUI.");
        guiIDInfuser = mainConfig.getInt(
                "infusion_table_main", category, 0, 0, Integer.MAX_VALUE,
                "ID for the Infusion Table main GUI."
        );

        // RARITIES
        final String categoryRarity = "rarities";
        mainConfig.addCustomCategoryComment(categoryRarity, "Define how rarely items should spawn in dungeon chests.");

        ItemUtils.getRegisteredEssences().forEach(essence -> {
            final String resourceName = ((Item) essence).getRegistryName().getPath();
            essence.setRarity(mainConfig.getInt(
                    resourceName,
                    categoryRarity,
                    essence.getRarity(),
                    0,
                    50,
                    "Spawn rarity for essence " + resourceName + "."
            ));
        });

        // MISCELLANEOUS
        category = "miscellaneous";
        mainConfig.addCustomCategoryComment(category, "Miscellaneous configurations.");
        hotReloadActive = mainConfig.getBoolean(
                "config_hot_reloadable",
                category,
                hotReloadActive,
                "Weather or not the configs should be reloadable by /msreloadconfig command."
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
    private void initStaffConfig(File file, GenericStaff staff) {
        final String cat = "properties";
        Configuration cfg = new Configuration(file);

        cfg.addCustomCategoryComment(cat, staff.getRegistryName() + " staff properties.");

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
            } else if (entry.getDef() instanceof Boolean) {
                entry.setCollected(
                        cfg.getBoolean(entry.getKey(), cat,
                                (boolean) entry.getDef(), entry.getComment())
                );
            }

            staff.configInitialized();
        });

        cfg.save();
    }
}
