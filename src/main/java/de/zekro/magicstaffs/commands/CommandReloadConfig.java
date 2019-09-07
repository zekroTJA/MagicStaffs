package de.zekro.magicstaffs.commands;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.util.CommandUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

/**
 * Command which can be used to reload configs.
 */
public class CommandReloadConfig extends CommandBase {

    @Override
    public String getName() {
        return "msreloadconfig";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "msreloadconfig";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!MagicStaffs.configHandler.hotReloadActive) {
            sender.sendMessage(new TextComponentString(
                    CommandUtil.getMessagePrefix() + TextFormatting.RED +
                    "Config hot reload is disabled by config."
            ));
            return;
        }

        sender.sendMessage(new TextComponentString(CommandUtil.getMessagePrefix() + "Reloading configs..."));
        MagicStaffs.configHandler.init();
        sender.sendMessage(new TextComponentString(CommandUtil.getMessagePrefix() + "Reload finished."));
    }
}
