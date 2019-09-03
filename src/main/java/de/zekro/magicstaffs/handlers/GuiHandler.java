package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.blocks.assembler.ContainerAssembler;
import de.zekro.magicstaffs.blocks.assembler.TileEntityAssembler;
import de.zekro.magicstaffs.gui.GuiAssembler;
import de.zekro.magicstaffs.gui.GuiIDs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == GuiIDs.ASSEMBLER)
            return new ContainerAssembler(player.inventory, (TileEntityAssembler) world.getTileEntity(new BlockPos(x, y, z)));

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == GuiIDs.ASSEMBLER)
            return new GuiAssembler(player.inventory, (TileEntityAssembler) world.getTileEntity(new BlockPos(x, y, z)));

        return null;
    }
}
