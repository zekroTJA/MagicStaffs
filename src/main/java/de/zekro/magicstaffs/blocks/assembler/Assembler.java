package de.zekro.magicstaffs.blocks.assembler;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.blocks.BlockBase;
import de.zekro.magicstaffs.gui.GuiIDs;
import de.zekro.magicstaffs.items.IHasModel;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Assembler extends BlockBase implements IHasModel, ITileEntityProvider {

    public Assembler(String name, CreativeTabs tabs) {
        super(name, Material.ROCK, tabs);
        fullBlock = false;
    }

    @Override
    public void registerModels() {
        MagicStaffs.proxy.registerModel(Item.getItemFromBlock(this), 0);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityAssembler();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!worldIn.isRemote) {
            if (worldIn.getTileEntity(pos) instanceof TileEntityAssembler) {
                playerIn.openGui(MagicStaffs.instance, GuiIDs.ASSEMBLER, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }

        return true;
    }
}
