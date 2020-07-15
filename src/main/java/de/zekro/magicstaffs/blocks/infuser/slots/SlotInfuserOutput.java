package de.zekro.magicstaffs.blocks.infuser.slots;

import de.zekro.magicstaffs.crafting.infuser.InfuserCraftingManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;

/**
 * Output slot for crafting results of the Infusion Table.
 */
@SuppressWarnings("NullableProblems")
public class SlotInfuserOutput extends SlotCrafting {

    private final InventoryCrafting craftMatrix;
    private final EntityPlayer player;

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
        ForgeHooks.setCraftingPlayer(thePlayer);
        NonNullList<ItemStack> nonNullList = InfuserCraftingManager.getRemainingItems(this.craftMatrix, thePlayer.world);
        ForgeHooks.setCraftingPlayer(null);

        for (int i = 0; i < nonNullList.size(); ++i) {
            ItemStack itemStack = this.craftMatrix.getStackInSlot(i);
            ItemStack itemStack1 = nonNullList.get(i);

            if (!itemStack.isEmpty()) {
                this.craftMatrix.decrStackSize(i, 1);
                itemStack = this.craftMatrix.getStackInSlot(i);
            }

            if (!itemStack1.isEmpty()) {
                if (itemStack.isEmpty()) {
                    this.craftMatrix.setInventorySlotContents(i, itemStack1);
                }
                else if (ItemStack.areItemsEqual(itemStack, itemStack1) && ItemStack.areItemStackTagsEqual(itemStack, itemStack1)) {
                    itemStack1.grow(itemStack.getCount());
                    this.craftMatrix.setInventorySlotContents(i, itemStack1);
                }
                else if (!this.player.inventory.addItemStackToInventory(itemStack1)) {
                    this.player.dropItem(itemStack1, false);
                }
            }
        }

        return stack;
    }
}
