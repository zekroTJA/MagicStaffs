package de.zekro.magicstaffs.crafting.infuser;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.crafting.infuser.recipes.StaffRecipe;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * Crafting manager for Infusion Table crafting recipes.
 */
public class InfuserCraftingManager {

    private static final List<IRecipe> REGISTRY = Arrays.asList(
            new StaffRecipe(MagicStaffs.ELECTRIC_STAFF),
            new StaffRecipe(MagicStaffs.FIRE_STAFF)
    );

    /**
     * Find matching registered recipe by passed crafting matrix and world.
     * @param craftMatrix crafting matrix
     * @param worldIn world the player is in
     * @return the matched recipe or null
     */
    @Nullable
    public static IRecipe findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn) {
        return REGISTRY
                .stream()
                .filter(recipe -> recipe.matches(craftMatrix, worldIn))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns remaining items after crafting.
     * @param craftMatrix crafting matrix
     * @param worldIn world the player is in
     * @return the slot map of the remaining item stacks
     */
    public static NonNullList<ItemStack> getRemainingItems(InventoryCrafting craftMatrix, World worldIn) {
        IRecipe recipe = findMatchingRecipe(craftMatrix, worldIn);

        if (recipe != null) {
            return recipe.getRemainingItems(craftMatrix);
        }

        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(craftMatrix.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            nonnulllist.set(i, craftMatrix.getStackInSlot(i));
        }

        return nonnulllist;
    }
}
