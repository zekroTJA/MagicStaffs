package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.blocks.infuser.ContainerInfuser;
import de.zekro.magicstaffs.blocks.infuser.TileEntityInfuser;
import de.zekro.magicstaffs.gui.GuiInfuser;
import de.zekro.magicstaffs.gui.GuiIDs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == GuiIDs.INFUSER)
            return new ContainerInfuser(player.inventory, (TileEntityInfuser) world.getTileEntity(new BlockPos(x, y, z)));

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == GuiIDs.INFUSER)
            return new GuiInfuser(player.inventory, (TileEntityInfuser) world.getTileEntity(new BlockPos(x, y, z)));

        return null;
    }
}
