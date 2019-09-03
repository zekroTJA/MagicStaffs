package de.zekro.magicstaffs.blocks.infuser;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.blocks.infuser.slots.SlotInfuserEssenceInput;
import de.zekro.magicstaffs.blocks.infuser.slots.SlotInfuserOutput;
import de.zekro.magicstaffs.blocks.infuser.slots.SlotInfuserStaffInput;
import de.zekro.magicstaffs.items.essences.ElectricEssence;
import de.zekro.magicstaffs.items.staffs.BaseStaff;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerInfuser extends Container {
    private final TileEntityInfuser entity;
    private final InventoryCraftResult result = new InventoryCraftResult();
    private World world;
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 1);

    public ContainerInfuser(InventoryPlayer player, TileEntityInfuser entity) {
        world = player.player.getEntityWorld();
        this.entity = entity;
        IItemHandler handler = entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        addSlotToContainer(new SlotInfuserStaffInput(craftMatrix, 0, 27, 33));
        addSlotToContainer(new SlotInfuserEssenceInput(craftMatrix, 1, 57, 33));
        addSlotToContainer(new SlotInfuserOutput(player.player, craftMatrix, result, 2, 127, 33));

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

        onCraftMatrixChanged(craftMatrix);
    }

    public void onCraftMatrixChanged(IInventory inventory) {
        // TODO: Replace this test recipe setup with proper
        //       external recipe management class handler.
        // TODO: Remove result item after one of the both recipe
        //       items was removed.
        if (craftMatrix.getStackInSlot(0).getItem() instanceof BaseStaff &&
            craftMatrix.getStackInSlot(1).getItem() instanceof ElectricEssence) {

            result.setInventorySlotContents(0, new ItemStack(MagicStaffs.ELECTRIC_STAFF));
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
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {

            ItemStack currStack = slot.getStack();
            stack = currStack.copy();

            if (index < 3) {
                if (!mergeItemStack(currStack, 3, 39, true))
                    return ItemStack.EMPTY;
                slot.onSlotChange(currStack, stack);
            } else {
                for (int i = 0; i < 2; ++i) {
                   Slot currSlot = inventorySlots.get(i);
                   if (currSlot.isItemValid(currStack) && mergeItemStack(currStack, i, i + 1, false)) {
                       return stack;
                   }
                }
                return ItemStack.EMPTY;
            }
        }

        return stack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        if (!world.isRemote)
            clearContainer(playerIn, world, this.craftMatrix);
    }
}
