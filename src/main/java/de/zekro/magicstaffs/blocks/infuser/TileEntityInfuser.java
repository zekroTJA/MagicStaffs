package de.zekro.magicstaffs.blocks.infuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Tile entity for the Infusion Table.
 */
public class TileEntityInfuser extends TileEntity implements ITickable {
    private final ItemStackHandler handler = new ItemStackHandler(3);

    private String customName;

    public int tickCount;

    public float tRot;
    public float bookRotation;

    /**
     * Returns if the tile entity has a custom name.
     * @return has custom name
     */
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    /**
     * Sets a custom name for the tile entity.
     * @param customName custom name
     */
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    /**
     * Returns if the container is usable by the passed
     * player entity.
     * @param player player entity which want to access the container
     * @return ability to access
     */
    public boolean isUsableByPlayer(EntityPlayer player) {
        return world.getTileEntity(this.pos) == this &&
                player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) this.handler;

        return super.getCapability(capability, facing);
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.infuser");
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.hasKey("CustomName", 8))
            this.setCustomName(compound.getString("CustomName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (this.hasCustomName())
            compound.setString("CustomName", this.customName);

        return compound;
    }

    public void update() {
        ++this.tickCount;

        while(this.bookRotation >= (float)Math.PI)
        {
            this.bookRotation -= ((float)Math.PI * 2F);
        }

        while(this.bookRotation < -(float)Math.PI)
        {
            this.bookRotation += ((float)Math.PI * 2F);
        }

        this.tRot += 0.02F;

        while(this.tRot >= (float)Math.PI)
        {
            this.tRot -= ((float)Math.PI * 2F);
        }

        while(this.tRot < -(float)Math.PI)
        {
            this.tRot += ((float)Math.PI * 2F);
        }

        float f2 = this.tRot - this.bookRotation;

        while(f2 >= (float)Math.PI)
        {
            f2 -= ((float)Math.PI * 2F);
        }

        while(f2 < -(float)Math.PI)
        {
            f2 += ((float)Math.PI * 2F);
        }

        this.bookRotation += f2 * 0.4F;
    }
}