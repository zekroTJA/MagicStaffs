package de.zekro.magicstaffs.blocks.assembler.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotAssemblerStaffInput extends SlotItemHandler {

    public SlotAssemblerStaffInput(IItemHandler handler, int index, int x, int y) {
        super(handler, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        // TODO: Check if item is a base staff
        return super.isItemValid(stack);
    }
}
