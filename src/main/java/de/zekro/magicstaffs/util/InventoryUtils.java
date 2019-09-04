package de.zekro.magicstaffs.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryUtils {

    public static ItemStack decreaseInventorySlot(IInventory inv, int slot, int amount) {
        ItemStack stack = inv.getStackInSlot(slot);
        int stackCount = stack.getCount();
        if (amount >= stackCount) {
            return ItemStack.EMPTY;
        }

        stack.setCount(stackCount - amount);
        return stack;
    }

}
