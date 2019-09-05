package de.zekro.magicstaffs.blocks.infuser.slots;

import de.zekro.magicstaffs.items.IBaseStaff;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Input slot for Staff for the Infusion Table.
 */
public class SlotInfuserStaffInput extends Slot {

    /**
     * Create new instance of SlotInfuserStaffInput.
     * @param inventoryIn inventory
     * @param index index of the slot
     * @param xPosition GUI X position
     * @param yPosition GUI Y position
     */
    public SlotInfuserStaffInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof IBaseStaff;
    }
}
