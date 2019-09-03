package de.zekro.magicstaffs.blocks.assembler;

import de.zekro.magicstaffs.blocks.assembler.slots.SlotAssemblerEssenceInput;
import de.zekro.magicstaffs.blocks.assembler.slots.SlotAssemblerOutput;
import de.zekro.magicstaffs.blocks.assembler.slots.SlotAssemblerStaffInput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerAssembler extends Container {
    private final TileEntityAssembler entity;

    public ContainerAssembler(InventoryPlayer player, TileEntityAssembler entity) {
        this.entity = entity;
        IItemHandler handler = entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        addSlotToContainer(new SlotAssemblerStaffInput(handler, 0, 27, 33));
        addSlotToContainer(new SlotAssemblerEssenceInput(handler, 1, 57, 33));
        addSlotToContainer(new SlotAssemblerOutput(handler, 2, 127, 33));

        // Inventory
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        // Hot Bar
        for (int x = 0; x < 9; ++x) {
            addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return entity.isUsableByPlayer(playerIn);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        System.out.println("CONTAINER CLOSED");
        super.onContainerClosed(playerIn);
    }
}
