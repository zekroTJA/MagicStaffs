package de.zekro.magicstaffs.util;

import net.minecraft.util.text.TextFormatting;

/**
 * Provides general utilities for commands.
 */
public class CommandUtil {

    /**
     * Returns the colored command chat output prefix.
     * @return general prefix
     */
    public static String getMessagePrefix() {
        return TextFormatting.WHITE + "[" + TextFormatting.AQUA + "MagicStaffs" + TextFormatting.WHITE + "] ";
    }

}
