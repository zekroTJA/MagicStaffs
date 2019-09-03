package de.zekro.magicstaffs.blocks.infuser;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.blocks.BlockBase;
import de.zekro.magicstaffs.gui.GuiIDs;
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

import java.util.Random;

public class BlockInfuser extends BlockBase implements ITileEntityProvider {

    public BlockInfuser(String name, CreativeTabs tabs) {
        super(name, Material.ROCK, tabs);
        setHardness(5);
        fullBlock = false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityInfuser();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!worldIn.isRemote) {
            if (worldIn.getTileEntity(pos) instanceof TileEntityInfuser) {
                playerIn.openGui(MagicStaffs.instance, GuiIDs.INFUSER, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }

        return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(MagicStaffs.INFUSER);
    }
}
