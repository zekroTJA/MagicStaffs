package de.zekro.magicstaffs.crafting.infuser.recipes;

import de.zekro.magicstaffs.items.staffs.BaseStaff;
import de.zekro.magicstaffs.tools.GenericStaff;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Recipe for staffs.
 */
public class StaffRecipe implements IRecipe {

    private final GenericStaff staff;

    /**
     * Create new instance of StaffRecipe.
     * @param staff the staff item instance
     */
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
}
