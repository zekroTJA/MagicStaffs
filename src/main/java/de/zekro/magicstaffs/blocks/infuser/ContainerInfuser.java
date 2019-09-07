package de.zekro.magicstaffs.blocks.infuser;

import de.zekro.magicstaffs.blocks.infuser.slots.SlotInfuserEssenceInput;
import de.zekro.magicstaffs.blocks.infuser.slots.SlotInfuserOutput;
import de.zekro.magicstaffs.blocks.infuser.slots.SlotInfuserStaffInput;
import de.zekro.magicstaffs.crafting.infuser.InfuserCraftingManager;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.world.World;

/**
 * The container for the Infusion Table.
 */
public class ContainerInfuser extends Container {

    private final TileEntityInfuser entity;
    private final InventoryCraftResult result = new InventoryCraftResult();

    private World world;
    private EntityPlayer player;

    private InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 1);

    /**
     * Create new instance of the container.
     * @param player inventory player instance
     * @param entity block tile entity instance
     */
    public ContainerInfuser(InventoryPlayer player, TileEntityInfuser entity) {
        this.world = player.player.getEntityWorld();
        this.player = player.player;
        this.entity = entity;

        addSlotToContainer(new SlotInfuserOutput(player.player, craftMatrix, result, 0, 127, 33));
        addSlotToContainer(new SlotInfuserStaffInput(craftMatrix, 1, 27, 33));
        addSlotToContainer(new SlotInfuserEssenceInput(craftMatrix, 2, 57, 33));

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
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.slotChangedCraftingGrid(world, player, craftMatrix, result);
    }

    @Override
    protected void slotChangedCraftingGrid(World world, EntityPlayer player, InventoryCrafting matrix, InventoryCraftResult result) {
        if (!world.isRemote) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP) player;
            ItemStack itemstack = ItemStack.EMPTY;
            IRecipe irecipe = InfuserCraftingManager.findMatchingRecipe(matrix, world);

            if (irecipe != null) {
                result.setRecipeUsed(irecipe);
                itemstack = irecipe.getCraftingResult(matrix);
            }

            result.setInventorySlotContents(0, itemstack);
            entityplayermp.connection.sendPacket(new SPacketSetSlot(windowId, 0, itemstack));
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
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {

            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (index == 0) {
                itemStack1.getItem().onCreated(itemStack1, world, playerIn);

                if (!mergeItemStack(itemStack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                inventorySlots.get(1).decrStackSize(1);
                inventorySlots.get(2).decrStackSize(1);

                slot.onSlotChange(itemStack1, itemStack);

                return itemStack;
            }
            else if (index < 3) {
                if (!mergeItemStack(itemStack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
            }
            else {
                for (int i = 1; i < 3; ++i) {
                   Slot currSlot = inventorySlots.get(i);
                   if (currSlot.isItemValid(itemStack1) && mergeItemStack(itemStack1, i, i + 1, false)) {
                       return itemStack;
                   }
                }
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                slot.onSlotChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
        }

        return itemStack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        if (!world.isRemote)
            clearContainer(playerIn, world, craftMatrix);
    }
}
