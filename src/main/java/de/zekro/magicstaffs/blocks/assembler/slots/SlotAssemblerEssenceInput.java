package de.zekro.magicstaffs.blocks.assembler.slots;

import de.zekro.magicstaffs.items.IEssence;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAssemblerEssenceInput extends Slot {

    public SlotAssemblerEssenceInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof IEssence;
    }
}
