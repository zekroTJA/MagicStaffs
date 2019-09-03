package de.zekro.magicstaffs.blocks.assembler.slots;

import de.zekro.magicstaffs.items.IBaseStaff;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAssemblerStaffInput extends Slot {


    public SlotAssemblerStaffInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof IBaseStaff;
    }
}
