package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.blocks.infuser.ContainerInfuser;
import de.zekro.magicstaffs.blocks.infuser.TileEntityInfuser;
import de.zekro.magicstaffs.gui.GuiInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * GUI Handler.
 */
public class GuiHandler implements IGuiHandler {

    public static final int ID_INFUSER = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == ID_INFUSER)
            return new ContainerInfuser(player.inventory, (TileEntityInfuser) world.getTileEntity(new BlockPos(x, y, z)));

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == ID_INFUSER)
            return new GuiInfuser(player.inventory, (TileEntityInfuser) world.getTileEntity(new BlockPos(x, y, z)));

        return null;
    }
}
