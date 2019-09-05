package de.zekro.magicstaffs.blocks.infuser.slots;

import de.zekro.magicstaffs.crafting.infuser.InfuserCraftingManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * Output slot for crafting results of the Infusion Table.
 */
public class SlotInfuserOutput extends SlotCrafting {

    private InventoryCrafting craftMatrix;
    private EntityPlayer player;

    /**
     * Create new instance of SlotInfuserOutput.
     * @param player player entity object
     * @param craftMatrix craft matrix inventory
     * @param result result inventory
     * @param slotIndex slot index
     * @param xPosition GUI X position
     * @param yPosition GUI Y position
     */
    public SlotInfuserOutput(EntityPlayer player, InventoryCrafting craftMatrix, IInventory result, int slotIndex, int xPosition, int yPosition) {
        super(player, craftMatrix, result, slotIndex, xPosition, yPosition);
        this.craftMatrix = craftMatrix;
        this.player = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        this.onCrafting(stack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(thePlayer);
        NonNullList<ItemStack> nonnulllist = InfuserCraftingManager.getRemainingItems(this.craftMatrix, thePlayer.world);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = this.craftMatrix.getStackInSlot(i);
            ItemStack itemstack1 = nonnulllist.get(i);

            if (!itemstack.isEmpty()) {
                this.craftMatrix.decrStackSize(i, 1);
                itemstack = this.craftMatrix.getStackInSlot(i);
            }

            if (!itemstack1.isEmpty()) {
                if (itemstack.isEmpty()) {
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                }
                else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1)) {
                    itemstack1.grow(itemstack.getCount());
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                }
                else if (!this.player.inventory.addItemStackToInventory(itemstack1)) {
                    this.player.dropItem(itemstack1, false);
                }
            }
        }

        return stack;
    }
}
