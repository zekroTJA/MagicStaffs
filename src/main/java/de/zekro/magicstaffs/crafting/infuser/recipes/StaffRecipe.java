package de.zekro.magicstaffs.crafting.infuser.recipes;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.items.IBaseStaff;
import de.zekro.magicstaffs.items.staffs.BaseStaff;
import de.zekro.magicstaffs.tools.GenericStaff;
import de.zekro.magicstaffs.util.InventoryUtils;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class StaffRecipe implements IRecipe {

    private GenericStaff staff;

    public StaffRecipe(GenericStaff staff) {
        this.staff = staff;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        final boolean isBaseStaff = inv.getStackInSlot(1).getItem() instanceof BaseStaff;
        final boolean isEssence = inv.getStackInSlot(2).getItem().getClass().isInstance(staff.getEssenceMadeOf());

        return isBaseStaff && isEssence;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return new ItemStack(staff);
    }

    @Override
    public boolean canFit(int width, int height) {
        return width == 3 && height == 1;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack(staff);
    }

    @Override
    public IRecipe setRegistryName(ResourceLocation name) {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return staff.getRegistryName();
    }

    @Override
    public Class<IRecipe> getRegistryType() {
        return null;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

//    @Override
//    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
//        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
//        ret.set(1, InventoryUtils.decreaseInventorySlot(inv, 1, 1));
//        ret.set(2, InventoryUtils.decreaseInventorySlot(inv, 2, 1));
//        return ret;
//    }
}
